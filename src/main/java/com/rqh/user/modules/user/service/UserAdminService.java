package com.rqh.user.modules.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rqh.user.modules.user.model.dto.UserQueryReqDTO;
import com.rqh.user.modules.user.model.entity.User;
import com.rqh.user.modules.user.model.vo.UserInfoVO;

public interface UserAdminService extends IService<User> {

    /**
     * 分页查询用户列表
     *
     * @param queryDTO 查询条件参数
     * @return 用户信息分页
     */
    Page<UserInfoVO> queryUserInfoPage(UserQueryReqDTO queryDTO);
}
