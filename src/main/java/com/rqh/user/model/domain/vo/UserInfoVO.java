package com.rqh.user.model.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 用户信息展示
 */
@Data
public class UserInfoVO {

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("用户名")
    private String userName;

    // 是否展示？展示是否要做脱敏
    @ApiModelProperty("账号")
    private String account;

    @ApiModelProperty("头像")
    private String avatar;

    // 是否展示？
    @ApiModelProperty("性别 0-女 1-男 2-保密")
    private Integer gender;

    // 是否展示？展示是否要做脱敏
    @ApiModelProperty("联系方式(手机号)")
    private String phone;

    // 是否要做脱敏
    @ApiModelProperty("邮箱地址")
    private String email;

    @ApiModelProperty("用户角色 0-管理员 1-普通用户 2-VIP")
    private Integer userRole;

    @ApiModelProperty("账号状态 0-禁用 1-正常")
    private Integer status;

    @ApiModelProperty("删除标识 0-未删除 1-已删除")
    private Integer deleted;

    @ApiModelProperty("创建时间")
    private Date createTime;

    // 是否展示
    @ApiModelProperty("更新时间")
    private Date updateTime;

    @ApiModelProperty("上次登录时间")
    private Date lastLoginTime;
}
