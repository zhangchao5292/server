package com.yy.springcloud.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author chao.zhang
 */

/**
 * 系统用户
 */
@ApiModel(value = "com-lumiclass-app-admin-model-entities-SysUser")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_user")
public class SysUser implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "", example = "0")
    private Integer id;

    /**
     * 员工id
     */
    @TableField(value = "user_id")
    @ApiModelProperty(value = "员工id")
    private String userId;

    /**
     * 员工直属领导id
     */
    @TableField(value = "leader_id")
    @ApiModelProperty(value = "员工直属领导id")
    private String leaderId;

    /**
     * 用户名
     */
    @TableField(value = "user_name")
    @ApiModelProperty(value = "用户名")
    private String userName;

    /**
     * 英文名
     */
    @TableField(value = "user_en_name")
    @ApiModelProperty(value = "英文名")
    private String userEnName;

    @TableField(value = "user_open_id")
    @ApiModelProperty(value = "")
    private String userOpenId;

    /**
     * 头像地址
     */
    @TableField(value = "avatar_url")
    @ApiModelProperty(value = "头像地址")
    private String avatarUrl;

    /**
     * 用户所在部门id，用户可能同时存在于多个部门
     */
    @TableField(value = "department_id")
    @ApiModelProperty(value = "用户所在部门id，用户可能同时存在于多个部门")
    private String departmentId;

    @ApiModelProperty(value = "用户所在部门名称，用户可能同时存在于多个部门")
    @TableField(exist = false)
    private List<String> departName;

    @TableField(value = "phone_num")
    @ApiModelProperty(value = "")
    private String phoneNum;

    @TableField(value = "email")
    @ApiModelProperty(value = "")
    private String email;

    /**
     * 岗位-角色ID
     */
    @TableField(value = "role_id")
    @ApiModelProperty(value = "岗位-角色ID")
    private String roleId;

    /**
     * 是否为管理员账号 0否 1是
     */
    @TableField(value = "is_admin")
    @ApiModelProperty(value = "是否为管理员账号 0否 1是", example = "0")
    private Integer isAdmin;

    /**
     * 创建者
     */
    @TableField(value = "created_by")
    @ApiModelProperty(value = "创建者")
    private String createdBy;

    /**
     * 更新着
     */
    @TableField(value = "updated_by")
    @ApiModelProperty(value = "更新者")
    private String updatedBy;

    @TableField(value = "city")
    @ApiModelProperty(value = "所在城市")
    private String city;

    /**
     * 创建日期
     */
    @TableField(value = "created_at")
    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createdAt;
    /**
     * 加入时间
     */
    @TableField(value = "join_time")
    @ApiModelProperty(value = "加入时间")
    private LocalDateTime joinTime;

    /**
     * 更新时间
     */
    @TableField(value = "updated_at", fill = FieldFill.UPDATE)
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedAt;

    /**
     * 状态：1删除、0启用
     */
    @TableField(value = "deleted")
    @ApiModelProperty(value = "状态：1删除、0启用", example = "0")
    private Integer deleted;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_LEADER_ID = "leader_id";

    public static final String COL_USER_NAME = "user_name";

    public static final String COL_USER_EN_NAME = "user_en_name";

    public static final String COL_USER_OPEN_ID = "user_open_id";

    public static final String COL_AVATAR_URL = "avatar_url";

    public static final String COL_DEPARTMENT_ID = "department_id";

    public static final String COL_PHONE_NUM = "phone_num";

    public static final String COL_EMAIL = "email";

    public static final String COL_ROLE_ID = "role_id";

    public static final String COL_IS_ADMIN = "is_admin";

    public static final String COL_CREATED_BY = "created_by";

    public static final String COL_UPDATED_BY = "updated_by";

    public static final String COL_CREATED_AT = "created_at";

    public static final String COL_UPDATED_AT = "updated_at";

    public static final String COL_DELETED = "deleted";
}