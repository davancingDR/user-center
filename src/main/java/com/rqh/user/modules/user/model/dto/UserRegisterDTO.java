package com.rqh.user.modules.user.model.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;

/**
 * 用户注册请求参数
 *
 */
@Data
public class UserRegisterDTO {

    @NotBlank(message = "用户名不能为空")
    private String userName;

    @NotBlank(message = "账号不能为空")
    @Size(min = 4, message = "账号长度不能小于 4")
    @Pattern(regexp = "^[^\\\\pP\\\\pS\\\\s]+$", message = "账号不能包含特殊字符")
    private String account;

    @NotBlank(message = "密码不能为空")
    @Size(min = 8, message = "密码长度不能小于 8")
    private String password;

    @NotBlank(message = "确认密码不能为空")
    @Size(min = 8, message = "两次密码输入结果不一致")
    private String checkPassword;
}
