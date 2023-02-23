package com.yy.springcloud.config;

import com.google.common.collect.ImmutableMap;
import com.yy.springcloud.interceptor.AuthenticationFilter;
import com.yy.springcloud.service.SysUserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;

/**
 * WebSecurityConfig
 *
 * @author tianyi.wei
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {//自定义Security策略

    @Autowired
    private SysUserLoginService sysUserLoginService;

    @Autowired
    private SpringProfilesConfig springProfilesConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                //需要登录验证的资源路径
                .requestMatchers(LUMI_REQUEST_MATCHER).authenticated()
                .anyRequest()
                .permitAll()
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedEntryPoint())
                .and()
                .httpBasic()
                .and()
                .headers().frameOptions().disable()
                .and()
                .addFilterBefore(new AuthenticationFilter(LUMI_REQUEST_MATCHER).setSysUserLoginService(sysUserLoginService, springProfilesConfig), BasicAuthenticationFilter.class);

    }

    /**
     * 需要登录才能访问的Get Path
     */
    public static String[] protectedGetPaths() {
        return AuthEndpointsConfig.authGetEndpoints().toArray(new String[0]);
    }

    /**
     * 需要登录才能访问的Post Path
     */
    public static String[] protectedPostPaths() {
        return AuthEndpointsConfig.authPostEndpoints().toArray(new String[0]);
    }

    /**
     * 需要登录才能访问的Delete Path
     */
    public static String[] protectedDeletePaths() {
        return AuthEndpointsConfig.authDelEndpoints().toArray(new String[0]);
    }


    public static final Map<HttpMethod, String[]> AUTH_MATCHER_MAP = ImmutableMap.of(
            HttpMethod.GET, protectedGetPaths(),
            HttpMethod.POST, protectedPostPaths(),
            HttpMethod.DELETE, protectedDeletePaths()
    );

    public static final RequestMatcher LUMI_REQUEST_MATCHER = new OrRequestMatcher(AUTH_MATCHER_MAP.entrySet()
            .stream()
            .map(entry -> mapToRequestMatcherArray(entry.getKey(), entry.getValue()))
            .flatMap(Arrays::stream)
            .toArray(RequestMatcher[]::new)
    );

    private static RequestMatcher[] mapToRequestMatcherArray(final HttpMethod httpMethod, String[] urlPatterns) {
        return Arrays.stream(urlPatterns)
                .map(urlPattern -> new AntPathRequestMatcher(urlPattern, httpMethod.name()))
                .toArray(RequestMatcher[]::new);
    }

    @Bean
    public AuthenticationEntryPoint unauthorizedEntryPoint() {
        return (request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
