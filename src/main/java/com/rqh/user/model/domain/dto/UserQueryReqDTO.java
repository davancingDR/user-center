package com.rqh.user.model.domain.dto;

import com.rqh.user.base.BaseQueryPage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserQueryReqDTO extends BaseQueryPage {

    /**
     * 用户名/昵称
     */
    private String userName;

    /**
     * 账号
     */
    private String account;

    /**
     * 性别 0-女 1-男 2-保密
     */
    private Integer gender;

    /**
     * 用户角色
     */
    private Integer userRole;

    /**
     * 账号状态 0-禁用 1-正常
     */
    private Integer status;

    /**
     * 排序字段：默认 userId, createTime, updateTime, lastLoginTime
     */
    private String sortField;

    /**
     * 排序方式 1-降序 2-升序
     */
    private Integer sortType;
}
