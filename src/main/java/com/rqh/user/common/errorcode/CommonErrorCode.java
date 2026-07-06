package com.rqh.user.common.errorcode;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 公共错误码：所有模块共用的成功码、系统错误、参数错误、认证/授权错误
 */
@Getter
@AllArgsConstructor
public enum CommonErrorCode implements IErrorCode {

    SUCCESS("0", "操作成功"),
    SYSTEM_ERROR("500", "系统异常，请稍后重试"),
    PARAMETER_ERROR("400", "参数错误"),
    UNAUTHORIZED("401", "当前账号未登录或登录已过期"),
    FORBIDDEN("403", "无访问权限")
    ;

    private final String code;

    private final String msg;

}
