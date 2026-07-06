package com.rqh.user.modules.user.errorcode;

import com.rqh.user.common.errorcode.IErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户模块错误码，编码规则：模块前缀（10=用户）+ 4 位业务码，全局不重复
 * 只放"用户业务"错误，参数错/系统错等公共错误在 {@CommonErrorCode}
 */
@Getter
@AllArgsConstructor
public enum UserErrorCode implements IErrorCode {

    ACCOUNT_EXIST("100001",     "账号已存在"),
    USER_NOT_EXIST("100002",    "用户不存在"),
    PASSWORD_WRONG("100003",    "账号或密码错误"),
    ACCOUNT_DISABLED("100004",  "账号已被禁用"),
    ;

    private final String code;

    private final String msg;
}
