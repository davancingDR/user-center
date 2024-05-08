package com.rqh.user.manager.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rqh.user.manager.UserManager;
import com.rqh.user.mapper.UserMapper;
import com.rqh.user.model.domain.dto.UserQueryReqDTO;
import com.rqh.user.model.domain.entity.User;
import com.rqh.user.model.domain.vo.UserInfoVO;

import javax.annotation.Resource;
import java.util.List;

public class UserManagerImpl implements UserManager {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> queryUserInfoPage(UserQueryReqDTO queryDTO) {
        LambdaQueryWrapper<UserInfoVO> queryWrapper = new LambdaQueryWrapper<>();

        return null;
    }
}
