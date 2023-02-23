package com.yy.springcloud.model;

import lombok.Builder;
import lombok.Data;

/**
 * LumiApiHeader
 *
 * @author tianyi.wei
 */
@Data
@Builder
public class LumiAdminHeader {

    private static final long serialVersionUID = 1L;

    private String sysUserId;

    private String isDebug;

}
