package com.rqh.user.modules.user.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserLoginDTO {

    // 账号
    @NotBlank(message = "账号不能为空")
    private String account;

    // 密码
    @NotBlank(message = "密码不能为空")
    @Size(min = 8, message = "密码长度不能小于 8")
    private String password;

    // 验证码
    private String verificationCode;
}
