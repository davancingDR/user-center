package com.rqh.user.common.errorcode;

/**
 * 错误码抽象接口
 * 所有错误码枚举都实现这个接口，全局异常处理器只依赖该类，不依赖具体枚举
 *
 */
public interface IErrorCode {

    /**
     * 获取错误码
     */
    String getCode();

    /**
     * 获取错误信息
     */
    String getMsg();
}
