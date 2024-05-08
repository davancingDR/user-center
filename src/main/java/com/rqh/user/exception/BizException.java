package com.rqh.user.exception;

import com.rqh.user.model.result.ResultCodeEnum;
import org.apache.commons.lang3.exception.ContextedRuntimeException;

import java.text.MessageFormat;

public class BizException extends ContextedRuntimeException {

    private ResultCodeEnum resultCode;

    public BizException(ResultCodeEnum resultCode) {
        super(resultCode.print());
        this.resultCode = resultCode;
    }

    public BizException(ResultCodeEnum resultCode, String message) {
        super(message);
        this.resultCode = new ResultCodeEnum() {
            @Override
            public String getCode() {
                return resultCode.getCode();
            }

            @Override
            public String getMsg() {
                return message;
            }
        };
    }

    public BizException(ResultCodeEnum resultCode, Object... args) {
        this.resultCode = new ResultCodeEnum() {
            @Override
            public String getCode() {
                return resultCode.getCode();
            }

            @Override
            public String getMsg() {
                return MessageFormat.format(resultCode.getMsg(), args);
            }
        };
    }

    public BizException(ResultCodeEnum resultCode, Throwable cause) {
        super(resultCode.toString(), cause);
        this.resultCode = resultCode;
    }

    public BizException(String resultCode, String message) {
        super(message);
        this.resultCode = new ResultCodeEnum() {
            @Override
            public String getCode() {
                return resultCode;
            }

            @Override
            public String getMsg() {
                return message;
            }
        };
    }

    public ResultCodeEnum getResultCode() {
        return resultCode;
    }
}

