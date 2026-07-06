package com.rqh.user.modules.user.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 用户信息展示
 */
@Data
public class UserInfoVO {

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "用户名")
    private String userName;

    // 是否展示？展示是否要做脱敏
    @Schema(description = "账号")
    private String account;

    @Schema(description = "头像")
    private String avatar;

    // 是否展示？
    @Schema(description = "性别 0-女 1-男 2-保密")
    private Integer gender;

    // 是否展示？展示是否要做脱敏
    @Schema(description = "联系方式(手机号)")
    private String phone;

    // 是否要做脱敏
    @Schema(description = "邮箱地址")
    private String email;

    @Schema(description = "用户角色 0-管理员 1-普通用户 2-VIP")
    private Integer userRole;

    @Schema(description = "账号状态 0-禁用 1-正常")
    private Integer status;

    @Schema(description = "删除标识 0-未删除 1-已删除")
    private Integer deleted;

    @Schema(description = "创建时间")
    private Date createTime;

    // 是否展示
    @Schema(description = "更新时间")
    private Date updateTime;

    @Schema(description = "上次登录时间")
    private Date lastLoginTime;
}
