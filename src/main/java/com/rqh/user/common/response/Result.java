package com.rqh.user.common.response;

import com.rqh.user.common.errorcode.CommonErrorCode;
import com.rqh.user.common.errorcode.IErrorCode;
import lombok.Data;

import java.io.Serializable;

/**
 * 统一响应体
 */
@Data
public class Result<T> implements Serializable  {

    private static final long serialVersionUID = 1L;

    private String code;

    private String message;

    private boolean success;

    private T data;

    private Result(String code, String message, boolean success, T data) {
        this.code = code;
        this.message = message;
        this.success = success;
        this.data = data;
    }

    public static <T> Result<T> success() {
        return success(null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(
                CommonErrorCode.SUCCESS.getCode(),
                CommonErrorCode.SUCCESS.getMsg(),
                true, data
        );
    }

    public static <T> Result<T> fail(IErrorCode errorCode) {
        return new Result<>(errorCode.getCode(), errorCode.getMsg(), false, null);
    }

    public static <T> Result<T> fail(IErrorCode errorCode, String message) {
        return new Result<>(errorCode.getCode(), message, false, null);
    }
}
