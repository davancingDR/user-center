package com.rqh.user.model.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户信息表
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {

    /**
     * 用户id
     */
    @TableId(type = IdType.AUTO)
    private Long userId;

    /**
     * 用户名/昵称
     */
    private String userName;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 性别 0-女 1-男 2-保密
     */
    private Integer gender;

    /**
     * 联系方式(手机号)
     */
    private String phone;

    /**
     * 邮箱地址
     */
    private String email;

    /**
     * 用户角色 0-管理员 1-普通用户 2-vip
     */
    private Integer userRole;

    /**
     * 账号状态 0-禁用 1-正常
     */
    private Integer status;

    /**
     * 删除标识 0-未删除 1-已删除
     */
    private Integer deleted;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 最近登录时间
     */
    private Date lastLoginTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}