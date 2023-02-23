package com.yy.springcloud.model;

import lombok.Getter;

/**
 * ApiResponseCode
 *
 * @author tianyi.wei
 */
public enum LumiApiResEnum {

    SUCCESS(20000, "成功"),

    SERVER_ERR(50000, "服务器内部逻辑发生错误"),

    AUTH_ERR(40000, "认证失败，请重新登录"),

    PERMISSION_ERR(40001, "没有权限"),

    REQUEST_PARAMETER_ERR(40002, "请求参数有错"),

    NOT_FOUND(40004, "数据未找到"),
    /**
     * 无效的入参
     */
    REQ_PARAM_INVALID(40005, "无效的入参"),

    /**
     * 找不到资源
     */
    /**
     * 参数不能为空
     */
    PARAM_ERR(40006, "参数不能为空：%s"),

    /**
     * 不支持操作
     */
    UN_SUPPORT_OPT(40007, "不支持操作"),
    /**
     * 记录不存在x
     */
    RECORD_NOT_FOUND(40008, "记录不存在"),

    FILE_OPT_ERROR(40009, "文件操作错误"),
    /**
     * 重定向地址异常
     */
    REDIRECT_URL_ERROR(40010, "重定向地址异常"),
    /**
     * 首页异常
     */
    REDIRECT_ADMIN_URL_ERROR(40011, "重定向首页异常"),

    FEI_SHU_TOKEN_ERROR(40012, "获取token失败"),

    FEI_SHU_ACCESS_ERROR(40013, "获取登录用户信息失败"),

    FEI_SHU_USER_INFO_ERROR(40015, "获取用户信息失败:%s"),

    REPEAT_SUBJECT_NAME_ERROR(40016, "学科已存在：%s"),

    REPEAT_QA_NOT_EXIST(40017, "题干id不存在"),

    REPEAT_QA_EXIST(40018, "当前题ID已配置"),

    UPLOAD_S3_FILE_ERROR(40020, "视频资源上传失败: %s"),

    GRAPH_DEL_CHECK_ERROR(40021, "关联了视频或题目资源，不可删除"),

    NULL_POINTER_EXCEPTION(40022, "null pointer exception"),

    ZEGO_TOKEN_FAIL(50001, "zego token fail"),

    UPLOAD_FILE_TYPE(50002, "只支持 pdf、ppt、pptx 文件"),

    COMMON_CUSTOMER_ERROR(50003, "%s"),

    DB_SAVE_ERROR(60000, "save error"),

    /**
     * Bitmovin encode wide vine异常
     */
    BITMOVIN_DRM_WIDE_VINE_XML_PARSE_ERROR(52000, "bitmovin encoding.... parse wideVine drm xml error"),

    /**
     * Bitmovin encode fair play fail异常
     */
    BITMOVIN_DRM_FAIR_PLAY_XML_PARSE_ERROR(52001, "bitmovin encoding.... parse fair play drm xml error"),

    /**
     * Bitmovin drm-crating fail
     */
    BITMOVIN_DRM_CREATE_FAILED(52002, "bitmovin drm crating failed...."),

    UPLOAD_VIDEO_STREAM_ERROR(51000, "can't get response from stream url"),

    UPLOAD_VIDEO_CONTENT_LENGTH_ERROR(51001, "video content length error");

    @Getter
    public final int code;

    @Getter
    public final String errMessage;

    LumiApiResEnum(int code, String errMessage) {
        this.code = code;
        this.errMessage = errMessage;
    }
}
