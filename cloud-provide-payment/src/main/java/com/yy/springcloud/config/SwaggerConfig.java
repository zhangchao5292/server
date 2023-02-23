package com.yy.springcloud.config;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.yy.springcloud.entity.SysUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.*;
import java.util.function.Predicate;

/**
 * SwaggerConfig
 *
 * @author tianyi.wei
 */
@Configuration
@EnableSwagger2
@Profile({"dev", "test"})
public class SwaggerConfig {

    @Bean
    public Docket customDocket() {
        return new Docket(DocumentationType.SWAGGER_2).
                apiInfo(apiInfo())
//                .pathMapping("/admin")
                .globalOperationParameters(globalParameters())
                .globalResponseMessage(RequestMethod.GET, responseMessages())
                .globalResponseMessage(RequestMethod.HEAD, responseMessages())
                .globalResponseMessage(RequestMethod.POST, responseMessages())
                .globalResponseMessage(RequestMethod.PUT, responseMessages())
                .globalResponseMessage(RequestMethod.PATCH, responseMessages())
                .globalResponseMessage(RequestMethod.DELETE, responseMessages())
                .globalResponseMessage(RequestMethod.OPTIONS, responseMessages())
                .globalResponseMessage(RequestMethod.TRACE, responseMessages())
                .protocols(protocols())
                .ignoredParameterTypes(SysUser.class)
                .consumes(Sets.newHashSet("application/x-www-form-urlencoded"))
                .produces(Sets.newHashSet("application/json"))
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yy.springcloud.controller"))
                .paths(PathSelectors.any())
                .build();

    }

    private List<Parameter> globalParameters() {
        return Lists.newArrayList(new ParameterBuilder().name("debugServer").description("客户端忽略").defaultValue("1")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build());
    }

    private HashSet<String> protocols() {
        return Sets.newHashSet("Http");
    }

    private List<ResponseMessage> responseMessages() {
        List<ResponseMessage> responseMessageList = new ArrayList<>();
        Map<String, Header> headers = new HashMap<>(4);
        responseMessageList.add(new ResponseMessageBuilder().code(20000).message("HTTP状态码-请求成功").headersWithDescription(headers).build());
        responseMessageList.add(new ResponseMessageBuilder().code(40002).message("HTTP状态码-请求参数错误").build());
        responseMessageList.add(new ResponseMessageBuilder().code(40001).message("HTTP状态码-未授权/授权失败").build());
        responseMessageList.add(new ResponseMessageBuilder().code(404).message("HTTP状态码-路由不存在").build());
        responseMessageList.add(new ResponseMessageBuilder().code(40005).message("HTTP状态码-不支持的请求方法").build());
        responseMessageList.add(new ResponseMessageBuilder().code(50000).message("HTTP状态码-服务器内部错误").build());
        return responseMessageList;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Lumi-Admin接口")
                .description("Lumi-Admin接口")
                .version("1.1")
                .build();
    }

    private List<ApiKey> securitySchemes() {
        return Lists.newArrayList(new ApiKey("JWT", "Authorization", "header"));
    }

    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts = new ArrayList<>();
        securityContexts.add(SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(input -> {
                    if (AuthEndpointsConfig.authGetEndpoints().stream().anyMatch((Predicate<String>) str -> str.equals(input))) {
                        return true;
                    }
                    if (AuthEndpointsConfig.authPostEndpoints().stream().anyMatch((Predicate<String>) str -> str.equals(input))) {
                        return true;
                    }
                    return AuthEndpointsConfig.authDelEndpoints().stream().anyMatch((Predicate<String>) str -> str.equals(input));
                })
                .build());
        return securityContexts;
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = {authorizationScope};
        List<SecurityReference> securityReferences = new ArrayList<>();
        securityReferences.add(new SecurityReference("JWT", authorizationScopes));
        return securityReferences;
    }

}
