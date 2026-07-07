package com.rqh.user.modules.user.manager;

import com.rqh.user.modules.user.model.dto.UserQueryReqDTO;
import com.rqh.user.modules.user.model.entity.User;

import java.util.List;

public interface UserManager {
    /**
     * 分页查询用户列表
     *
     * @param queryDTO 查询参数
     * @return 用户列表
     */
    List<User> queryUserInfoPage(UserQueryReqDTO queryDTO);

    /**
     * 根据账号查询用户信息
     * @param account 用户账号
     * @return 用户信息
     */
    User queryUser(String account);

}
