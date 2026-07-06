package com.rqh.user.common.exception;

import com.rqh.user.common.errorcode.IErrorCode;
import lombok.Getter;

/**
 * 业务异常
 */
@Getter
public class BusinessException extends RuntimeException {

    private final IErrorCode errorCode;

    public BusinessException(IErrorCode errorCode) {
        super(errorCode.getMsg());
        this.errorCode = errorCode;
    }

    public BusinessException(IErrorCode errorCode, String message) {
        super(message);
        this.errorCode = new IErrorCode() {
            @Override
            public String getCode() {
                return errorCode.getCode();
            }

            @Override
            public String getMsg() {
                return message;
            }
        };
    }
}
