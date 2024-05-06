package com.rqh.user.model.enums;

import com.rqh.user.model.result.ResultCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 返回错误码
 */
@Getter
@AllArgsConstructor
public enum ResponseCodeEnum implements ResultCodeEnum {

    SYSTEM_ERROR("0", "系统异常"),
    SUCCESS("1", "操作成功"),
    PARAMETER_ERROR("2", "参数错误"),
    ;

    String code;
    String msg;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
