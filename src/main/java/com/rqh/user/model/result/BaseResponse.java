package com.rqh.user.model.result;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class BaseResponse implements Serializable {

    /**
     * 返回状态码
     */
    public final String code;

    /**
     * 返回信息内容
     */
    private final String message;

    private final boolean success;

    public BaseResponse() {
        this.code = ResponseCodeEnum.SUCCESS.getCode();
        this.message = ResponseCodeEnum.SUCCESS.getMsg();
        this.success = ResponseCodeEnum.SUCCESS.getCode().equals(getCode());
    }

    public BaseResponse(ResponseCodeEnum code) {
        this.code = code.getCode();
        this.message = code.getMsg();
        this.success = ResponseCodeEnum.SUCCESS.getCode().equals(getCode());
    }

    public BaseResponse(ResponseCodeEnum code, String message) {
        this.code = code.getCode();
        this.message = message;
        this.success = ResponseCodeEnum.SUCCESS.getCode().equals(getCode());
    }
}
