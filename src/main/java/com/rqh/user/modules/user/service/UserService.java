package com.rqh.user.modules.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rqh.user.modules.user.model.dto.UserQueryReqDTO;
import com.rqh.user.modules.user.model.dto.UserRegisterDTO;
import com.rqh.user.modules.user.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rqh.user.modules.user.model.vo.UserInfoVO;

/**
* @author L.Snow
* @description 针对表【user(用户信息表)】的数据库操作 Service
* @createDate 2024-05-03 23:25:50
*/
public interface UserService extends IService<User> {

    /**
     * 用户注册
     *
     * @param userRegisterDTO 注册参数
     * @return 用户id
     */
    long userRegister(UserRegisterDTO userRegisterDTO);

    /**
     * 分页查询用户列表
     *
     * @param queryDTO 查询条件参数
     * @return 用户信息分页
     */
    Page<UserInfoVO> queryUserInfoPage(UserQueryReqDTO queryDTO);
}
