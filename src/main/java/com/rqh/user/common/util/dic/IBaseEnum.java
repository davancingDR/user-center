package com.rqh.user.common.util.dic;

/**
 * 字典基础枚举类
 */
public interface IBaseEnum {

    String KEY = "key";

    String VALUE = "value";

    /**
     * 获取key值
     *
     * @return key
     */
    String getKey();

    /**
     *获取value值
     *
     * @return value
     */
    String getValue();
}
