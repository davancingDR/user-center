package com.rqh.user.model.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 用户注册请求参数
 *
 */
@Data
public class UserRegisterDTO {

    @NotBlank(message = "用户名不能为空")
    private String userName;

    @NotBlank(message = "账号不能为空")
    private String account;

    @NotBlank(message = "密码不能为空")
    private String password;

    @NotBlank(message = "确认密码不能为空")
    private String checkPassword;
}
