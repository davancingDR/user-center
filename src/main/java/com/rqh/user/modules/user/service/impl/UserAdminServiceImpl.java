package com.rqh.user.modules.user.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rqh.user.common.util.CopyUtil;
import com.rqh.user.modules.user.manager.UserManager;
import com.rqh.user.modules.user.mapper.UserMapper;
import com.rqh.user.modules.user.model.dto.UserQueryReqDTO;
import com.rqh.user.modules.user.model.entity.User;
import com.rqh.user.modules.user.model.vo.UserInfoVO;
import com.rqh.user.modules.user.service.UserAdminService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAdminServiceImpl extends ServiceImpl<UserMapper, User> implements UserAdminService {

    private UserManager userManager;

    @Override
    public Page<UserInfoVO> queryUserInfoPage(UserQueryReqDTO queryDTO) {
        Page<UserInfoVO> userInfoPage = new Page<>();
        List<User> userList = userManager.queryUserInfoPage(queryDTO);
        List<UserInfoVO> userVoList = CopyUtil.copyList(userList, UserInfoVO.class);
        userInfoPage.setRecords(userVoList);
        return userInfoPage;
    }
}
