package com.rqh.user.model.domain.dto;

import lombok.Data;

@Data
public class UserLoginDTO {

    // 账号
    private String account;

    // 密码
    private String password;

    // 验证码
    private String verificationCode;
}
