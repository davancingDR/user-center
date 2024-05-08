package com.rqh.user.exception;

import com.rqh.user.model.enums.ExceptionCodeEnum;
import com.rqh.user.model.result.ResultCodeEnum;

public class UserException extends BizException {
    public UserException(ExceptionCodeEnum codeEnum) {
        super((ResultCodeEnum) codeEnum);
    }

    public UserException(ExceptionCodeEnum codeEnum, String msg) {
        super((ResultCodeEnum) codeEnum, msg);
    }

    public UserException(ExceptionCodeEnum codeEnum, Throwable cause) {
        super((ResultCodeEnum) codeEnum, cause);
    }
}
