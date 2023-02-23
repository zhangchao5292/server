package com.yy.springcloud.interceptor;

import com.alibaba.fastjson.JSON;
import com.google.common.net.HttpHeaders;
import com.yy.springcloud.ApiConstants;
import com.yy.springcloud.config.SpringProfilesConfig;
import com.yy.springcloud.entity.SysUser;
import com.yy.springcloud.model.LumiApiResEnum;
import com.yy.springcloud.model.LumiApiResponse;
import com.yy.springcloud.security.UserAuthentication;
import com.yy.springcloud.service.SysUserLoginService;
import com.yy.springcloud.utils.JwtCreator;
import com.yy.springcloud.utils.StringUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * AuthenticationFilter
 *
 * @author tianyi.wei
 */
@Slf4j
public class AuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private SysUserLoginService sysUserLoginService;

    private SpringProfilesConfig springProfilesConfig;

    public Filter setSysUserLoginService(SysUserLoginService sysUserLoginService, SpringProfilesConfig springProfilesConfig) {
        this.sysUserLoginService = sysUserLoginService;
        this.springProfilesConfig = springProfilesConfig;
        return this;
    }

    public AuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String clientIp = StringUtil.getIpAddress(request);
        String clientType = StringUtil.getClientType(request);
        log.info("attemptAuthentication clientIp:{}, clientType:{}, page:{}", clientIp, clientType, request.getRequestURL());
        String debugServer = request.getHeader("debugServer");
        if (StringUtils.isNotEmpty(debugServer) && (springProfilesConfig.getProfiles().equals(ApiConstants.PROFILE_DEV) || springProfilesConfig.getProfiles().equals(ApiConstants.PROFILE_TEST))) {
            return new UserAuthentication(new SysUser());
        }

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        Claims claims = JwtCreator.parseJwt(header, "6L2s5YyW5Li6NOS4qjbkvY3nmoTlrZfoioI=");
        if (null == claims) {
            log.info("attemptAuthentication Invalid token");
            throw new BadCredentialsException("Invalid token");
        }
        String uid = claims.get("userId").toString();
        if (StringUtils.isEmpty(uid) || "0".equals(uid)) {
            log.info("attemptAuthentication Invalid uid:{}", uid);
            throw new BadCredentialsException("Invalid uid");
        }
        request.setAttribute(ApiConstants.HTTP_HEADER_X_LUMI_UID, uid);
        //查询用户
        SysUser sysUser=SysUser.builder()
                .userEnName("jack")
                .id(1)
                .userId("12332")
                .userName("wew")
                .roleId("1")
                .build();
        System.out.println(JSON.toJSONString(sysUser));
        return new UserAuthentication(sysUser);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authResult);
        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response, AuthenticationException failed)
            throws IOException {
        SecurityContextHolder.clearContext();
        if (logger.isDebugEnabled()) {
            logger.info("Authentication request failed: " + failed.toString());
            logger.info("Updated SecurityContextHolder to contain null Authentication");
        }
        String apiResponseStr = JSON.toJSONString(new LumiApiResponse(LumiApiResEnum.AUTH_ERR, "authentication error"));
        if (failed instanceof BadCredentialsException) {
            log.warn("{}: {}", apiResponseStr, failed.toString());
        } else {
            log.error(apiResponseStr, failed);
        }
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(apiResponseStr);
    }


}
