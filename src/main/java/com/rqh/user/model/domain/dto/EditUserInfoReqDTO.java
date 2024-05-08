package com.rqh.user.model.domain.dto;

import lombok.Data;

@Data
public class EditUserInfoReqDTO {
    /**
     * 用户名/昵称
     */
    private String userName;

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
}
