package com.rqh.user.model.domain.dto;

import lombok.Data;

@Data
public class EditPasswordReqDTO {

    // 账号
    private String account;

    // 原密码
    private String oldPassword;

    // 新密码
    private String password;

    // 确认新密码
    private String checkPassword;
}
