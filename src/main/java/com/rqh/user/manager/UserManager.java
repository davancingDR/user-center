package com.rqh.user.manager;

import com.rqh.user.model.domain.dto.UserQueryReqDTO;
import com.rqh.user.model.domain.entity.User;

import java.util.List;

public interface UserManager {
    /**
     * 分页查询用户列表
     *
     * @param queryDTO 查询参数
     * @return 用户列表
     */
    List<User> queryUserInfoPage(UserQueryReqDTO queryDTO);
}
