package com.rqh.user.modules.user.manager.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rqh.user.modules.user.manager.UserManager;
import com.rqh.user.modules.user.mapper.UserMapper;
import com.rqh.user.modules.user.model.dto.UserQueryReqDTO;
import com.rqh.user.modules.user.model.entity.User;
import com.rqh.user.modules.user.model.vo.UserInfoVO;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;
import java.util.List;

@Component
public class UserManagerImpl implements UserManager {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> queryUserInfoPage(UserQueryReqDTO queryDTO) {
        LambdaQueryWrapper<UserInfoVO> queryWrapper = new LambdaQueryWrapper<>();

        return null;
    }
}
