package com.rqh.user.model.enums;

import com.rqh.user.model.result.ResultCodeEnum;

public enum UserExceptionEnum implements ExceptionCodeEnum<UserExceptionEnum>, ResultCodeEnum {

    // 用户异常
    LOGIN_NAME_BLANK("10000001", "登录名不能为空"),
    LOGIN_NAME_EXIST("24010005", "账号已存在"),
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
