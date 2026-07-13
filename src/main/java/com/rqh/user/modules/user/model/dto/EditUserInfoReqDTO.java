package com.rqh.user.modules.user.model.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class EditUserInfoReqDTO {
    /**
     * 用户名/昵称
     */
    @Size(max = 64, message = "用户名长度不能超过 64")
    private String userName;

    /**
     * 用户头像
     */
    @Size(max = 255, message = "头像长度不能超过 255")
    private String avatar;

    /**
     * 性别 0-女 1-男 2-保密
     */
    @Min(value = 0, message = "性别参数错误")
    @Max(value = 2, message = "性别参数错误")
    private Integer gender;

    /**
     * 联系方式(手机号)
     */
    @Pattern(regexp = "^1\\d{10}$", message = "手机号格式错误")
    private String phone;

    /**
     * 邮箱地址
     */
    @Email(message = "邮箱格式错误")
    @Size(max = 128, message = "邮箱长度不能超过 128")
    private String email;
}
