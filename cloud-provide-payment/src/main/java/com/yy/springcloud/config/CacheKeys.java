package com.yy.springcloud.config;


/**
 * Author: ellios
 * Date: 2020/3/13
 */
public interface CacheKeys {

    Long CACHE_TIME_HOUR_ONE = 60 * 60L;

    Long CACHE_TIME_DAY_ONE = CACHE_TIME_HOUR_ONE * 24L;

    String CACHE_NAMESPACE = "wmv_cache";

    String CLIENT_VERSION_CONFIG = "client_version_config";

    // 消息分片缓存
    String MESSAGE_SEGMENT_CACHE = CACHE_NAMESPACE + "::message_segment_";

    String LIVE_ROOM_CACHE_PREFIX = "live_room_";
    String LIVE_VIDEO_CACHE_PREFIX = "live_video_";
    String VIDEO_PLAYURL_CACHE_PREFIX = CACHE_NAMESPACE + "::video_playurl_";

    String ROOM_VIDEO_CACHE_PREFIX = CACHE_NAMESPACE + "::room_video_list_";
    String ROOM_SECTION_VIDEO_CACHE_PREFIX = CACHE_NAMESPACE + "::room_section_video_list_";
    String DETAIL_LIVE_ROOM_CACHE_PREFIX = "detail_live_room_";
    String LIVE_ROOM_ENTITY_CACHE_PREFIX = "live_room_e_";
    String ENTER_LIVE_ROOM_CACHE_PREFIX = "enter_live_room_";
    String LIVE_ROOM_GROUP_ENTITY_CACHE_PREFIX = "live_room_group_e_";
    String SCOPE_MESSAGE_FORBID_ENTITY_CACHE_PREFIX = "scope_message_forbid_e_";
    String COURSE_CLASS_INFO_CACHE_PREFIX = "course_class_info_";
    String CQ_CLASS_INFO_CACHE_PREFIX = "cq_class_info_";
    String LIVING_ROOM_ID_SET_KEY = CACHE_NAMESPACE + "::living_room_id_set";
    //不同供应商的在播直播间id集合
    String VENDOR_LIVING_ROOM_ID_SET_KEY = CACHE_NAMESPACE + "::%s_living_room_id_set";
    String LIVING_ROOM_SECTION_KEY_PREFIX = CACHE_NAMESPACE + "::living_room_section_";
    //String LIVING_ROOM_USER_SET_KEY_PREFIX = CACHE_NAMESPACE + "::living_room_uids_";
    //String LIVING_ROOM_GROUP_USER_SET_KEY_PREFIX = CACHE_NAMESPACE + "::living_group_uids_";
    // 直播间分组缓存
    String LIVING_ROOM_GROUP_KEY_PREFIX = CACHE_NAMESPACE + "::living_room_group_";
    String LIVING_ROOM_USER_COUNT_KEY_PREFIX = CACHE_NAMESPACE + "::living_room_uc_";
    String LIVING_ROOM_GROUP_USER_COUNT_KEY_PREFIX = CACHE_NAMESPACE + "::living_group_uc_";
    String LIVE_CONFIG_KEY_PREFIX = CACHE_NAMESPACE + "::live_config_";

    String IM_ONLINE_USER_KEY_PREFIX = CACHE_NAMESPACE + "::im_online_user_";
    String GROUP_ONLINE_USER_SET_KEY_PREFIX = CACHE_NAMESPACE + "::group_online_user_set_";
    String VIDEO_WATCHING_USER_SET_MINUTE_KEY_PREFIX = CACHE_NAMESPACE + "::video_watching_user_set_minute_";
    String LIVE_WATCHING_USER_SET_MINUTE_KEY_PATTERN = CACHE_NAMESPACE + "::lr_%s_watching_user_set_minute_%s";

    String MESSAGE_SENSITIVE_WORD = CACHE_NAMESPACE + "::message_sensitive_word_";

    String MESSAGE_FP_ID_PREFIX = CACHE_NAMESPACE + "::message_fp_id_";
    String MESSAGE_FP_KEY_PREFIX = CACHE_NAMESPACE + "::message_fp_key_";
    String MESSAGE_PLAYBACK_GROUP_MINUTE_KEY_FORMAT = CACHE_NAMESPACE + "::message_playback_%s_group_%s_segment_%s";
    String NOTIFICATION_PLAYBACK_GROUP_KEY_FORMAT = CACHE_NAMESPACE + "::notification_playback_%s_group_%s";

    // 自定义信令发送限制
    String MESSAGE_SEND_LIMIT_PREFIX = CACHE_NAMESPACE + "::message_send_limit_%s_%s";
    String MESSAGE_SECTION_SEND_LIMIT_PREFIX = CACHE_NAMESPACE + "::message_section_send_limit_%s_%s_%s";

}
