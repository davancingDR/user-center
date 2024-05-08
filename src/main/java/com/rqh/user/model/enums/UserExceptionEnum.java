package com.rqh.user.model.enums;

import com.rqh.user.model.result.ResultCodeEnum;

public enum UserExceptionEnum implements ExceptionCodeEnum<UserExceptionEnum>, ResultCodeEnum {

    // 参数异常
    PARAMETER_ERROR("10000001", "数据格式错误"),
    PARAMETER_IS_NULL("10000002", "参数不能为空"),

    // 用户异常
    LOGIN_NAME_BLANK("10000001", "登录名不能为空"),
    ACCOUNT_EXIST("10000004", "账号已存在"),
    USER_NOT_EXIST("10000002", "用户不存在"),
    ;

    private String code, msg;

    UserExceptionEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public UserExceptionEnum msg(String msg) {
        this.msg = msg;
        return this;
    }

    @Override
    public String getDetail() {
        return ExceptionCodeEnum.super.getDetail();
    }
}
