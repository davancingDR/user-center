package com.rqh.user.modules.user.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 账号状态枚举类
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum AccountStatusEnum {

    BAN(0, "禁用"),
    NORMAL(1, "正常"),
    LIMITED(2, "受限"),
    ;

    Integer code;
    String msg;
}
