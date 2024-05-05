package com.rqh.user.service;

import com.rqh.user.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author L.Snow
* @description 针对表【user(用户信息表)】的数据库操作 Service
* @createDate 2024-05-03 23:25:50
*/
public interface UserService extends IService<User> {

    /**
     * 用户注册
     * @param account 用户账号
     * @param password 用户密码
     * @param checkPassword 校验密码
     * @return 用户id
     */
    // todo 封装入参
    long userRegister(String account, String password, String checkPassword);
}
