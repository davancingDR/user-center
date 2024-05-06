package com.rqh.user.model.enums;

/**
 * 所有异常码的公共接口
 * @param <T>
 */
public interface ExceptionCodeEnum<T> {

    /**
     * 错误码
     *
     * @return code
     */
    String getCode();

    /**
     * 错误描述
     *
     * @return msg
     */
    String getMsg();

    /**
     * 重新设置错误提示
     *
     * @param msg
     * @return
     */
    default T msg(String msg) {
        throw new IllegalArgumentException("请实现该方法");
    }

    /**
     * 获取错误详情，用于输出记录
     *
     * @return code, msg
     */
    default String getDetail() {
        return "code: " + getCode() + ", msg: " + getMsg();
    }
}
