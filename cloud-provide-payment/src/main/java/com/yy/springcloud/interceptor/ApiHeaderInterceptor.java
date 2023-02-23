package com.yy.springcloud.interceptor;

import com.yy.springcloud.config.ApiHeaderHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ApiHeaderInterceptor
 *
 * @author tianyi.wei
 */
@Slf4j
@Component
public class ApiHeaderInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        ApiHeaderHolder.put(request);
        if ("1".equals(ApiHeaderHolder.getIsDebug())) {
            log.info("Debug Headers:" + ApiHeaderHolder.get());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        ApiHeaderHolder.clear();
    }


}
