package com.rqh.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rqh.user.model.domain.dto.UserRegisterDTO;
import com.rqh.user.model.domain.entity.User;
import com.rqh.user.mapper.UserMapper;
import com.rqh.user.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* @author L.Snow
* @description 针对表【user(用户信息表)】的数据库操作 Service 实现
* @createDate 2024-05-03 23:25:50
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

    @Override
    public long userRegister(UserRegisterDTO userRegisterDTO) {
        // 1. 数据格式校验
        // 非空校验
        if (StringUtils.isAnyBlank(userRegisterDTO.getAccount(),
                userRegisterDTO.getPassword(), userRegisterDTO.getCheckPassword())) {
            return -1;
        }
        if (userRegisterDTO.getAccount().length() < 4) {
            return -1;
        }
        if (userRegisterDTO.getPassword().length() < 8 || userRegisterDTO.getCheckPassword().length() < 8) {
            return -1;
        }
        // 账号不能含有特殊字符
        String validPattern = "\\pP|\\pS|\\s+";
        // \s+ 一个空格或多个，不管在哪个位置都能匹配
        // \pP p 是 property 的意思，表示 Unicode 属性，用于 Unicode 正则表达式的前缀
        // P 表示 Unicode 字符集属性：标点字符，S 是符号（数学符号，货币符号等）
        Matcher matcher = Pattern.compile(validPattern).matcher(userRegisterDTO.getAccount());
        if (!matcher.find()) {
            return -1;
        }
        //  密码和校验密码相同
        if (!userRegisterDTO.getPassword().equals(userRegisterDTO.getCheckPassword())) {
            return -1;
        }
        // 账号不能重复
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", userRegisterDTO.getAccount());
        long count = this.count(queryWrapper);
        if (count > 0) {
            return -1;
        }

        // 2. 加密

        return 0;
    }
}




