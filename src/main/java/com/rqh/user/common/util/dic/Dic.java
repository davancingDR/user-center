package com.rqh.user.common.util.dic;

/**
 * 字典
 */
public class Dic implements IBaseEnum {

    private String key;

    private String value;

    @Override
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
