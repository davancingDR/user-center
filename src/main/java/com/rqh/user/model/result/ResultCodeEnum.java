package com.rqh.user.model.result;

import java.text.MessageFormat;

/**
 * 返回的错误码
 */
public interface ResultCodeEnum {

    /**
     * 获取错误码
     * @return code
     */
    String getCode();

    /**
     * 获取错误信息
     * @return  msg
     */
    String getMsg();

    default String print() {
        return "ResultCode { code = " + this.getCode() + '\'' + ", msg = " + this.getMsg() + '}';
    }

    default String print(String message) {
        return "ResultCode { code = " + this.getCode() + '\'' + ", msg = " + message + '}';
    }

    default String print(Object... args) {
        return "ResultCode { code = " + this.getCode() + '\'' + ", msg = " + MessageFormat.format(this.getMsg(), args) + '}';
    }
}
