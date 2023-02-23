package com.yy.springcloud.config;


import com.yy.springcloud.ApiConstants;
import com.yy.springcloud.model.LumiAdminHeader;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * ApiHeaderHolder
 *
 * @author tianyi.wei
 */
public class ApiHeaderHolder {

    private static final ThreadLocal<LumiAdminHeader> HOLDER = new ThreadLocal<>();

    public static void put(HttpServletRequest request) {
        Object attribute = request.getAttribute(ApiConstants.HTTP_HEADER_X_LUMI_UID);
        HOLDER.set(LumiAdminHeader.builder()
                .sysUserId(null == attribute ? "" : attribute.toString())
                .build());
    }

    public static void clear() {
        HOLDER.remove();
    }

    public static LumiAdminHeader get() {
        return HOLDER.get();
    }

    public static String getIsDebug() {
        return Optional.ofNullable(HOLDER.get()).isPresent() ? HOLDER.get().getIsDebug() : "";
    }
}
