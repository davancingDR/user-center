package com.rqh.user.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 用户角色枚举类
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum UserRoleEnum {

    ADMIN(0, "管理员"),
    COMMON(1, "普通用户"),
    VIP(2, "VIP 用户"),
    ;

    Integer code;
    String msg;
}
