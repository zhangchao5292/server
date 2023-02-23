package com.yy.springcloud.config;

import com.google.common.collect.Lists;

import java.util.ArrayList;

/**
 * AuthEndpointsConfig 需要登录用户权限的endpoint需要在这里配置路径，让@LumiUser可以访问
 *
 * @author tianyi.wei
 */
public class AuthEndpointsConfig {

    public static ArrayList<String> authGetEndpoints() {
        return Lists.newArrayList("/api/**");
    }

    public static ArrayList<String> authPostEndpoints() {
        return Lists.newArrayList("/api/**");

    }

    public static ArrayList<String> authDelEndpoints() {
        return Lists.newArrayList("/api/**");
    }

}
