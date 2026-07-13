package com.rqh.user.modules.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rqh.user.common.errorcode.CommonErrorCode;
import com.rqh.user.common.exception.BusinessException;
import com.rqh.user.common.util.CopyUtil;
import com.rqh.user.modules.user.manager.UserManager;
import com.rqh.user.modules.user.mapper.UserMapper;
import com.rqh.user.modules.user.model.dto.EditUserInfoReqDTO;
import com.rqh.user.modules.user.model.dto.UserLoginDTO;
import com.rqh.user.modules.user.model.dto.UserRegisterDTO;
import com.rqh.user.modules.user.model.entity.User;
import com.rqh.user.modules.user.model.vo.UserInfoVO;
import com.rqh.user.modules.user.enums.AccountStatusEnum;
import com.rqh.user.modules.user.errorcode.UserErrorCode;
import com.rqh.user.modules.user.enums.UserRoleEnum;
import com.rqh.user.modules.user.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

import java.util.Objects;

/**
* @author L.Snow
* @description 针对表【user(用户信息表)】的数据库操作 Service 实现
* @createDate 2024-05-03 23:25:50
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private UserManager userManager;

    @Override
    public long userRegister(UserRegisterDTO userRegisterDTO) {
        // 1. 数据格式校验
//        if (userRegisterDTO.getAccount().length() < 4) {
//            throw new BusinessException(CommonErrorCode.PARAMETER_ERROR, "账号长度不能小于4");
//        }
//        if (userRegisterDTO.getPassword().length() < 8 || userRegisterDTO.getCheckPassword().length() < 8) {
//            throw new BusinessException(CommonErrorCode.PARAMETER_ERROR, "密码长度不能小于8");
//        }
//        // 账号不能含有特殊字符
//        String validPattern = "\\pP|\\pS|\\s+";
//        // \s+ 一个空格或多个，不管在哪个位置都能匹配
//        // \pP p 是 property 的意思，表示 Unicode 属性，用于 Unicode 正则表达式的前缀
//        // P 表示 Unicode 字符集属性：标点字符，S 是符号（数学符号，货币符号等）
//        Matcher matcher = Pattern.compile(validPattern).matcher(userRegisterDTO.getAccount());
//        if (matcher.find()) {
//            throw new BusinessException(CommonErrorCode.PARAMETER_ERROR, "账号不能包含特殊字符");
//        }
        //  密码和校验密码相同
        if (!userRegisterDTO.getPassword().equals(userRegisterDTO.getCheckPassword())) {
            throw new BusinessException(CommonErrorCode.PARAMETER_ERROR, "两次输入的密码不一致");
        }
        // 账号不能重复
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getAccount, userRegisterDTO.getAccount());
        long count = this.count(queryWrapper);
        if (count > 0) {
            throw new BusinessException(UserErrorCode.ACCOUNT_EXIST);
        }

        // 2. 加密：BCrypt 自带随机盐，直接调用 encode 方法，不用自己拼盐
        String encryptPassword = passwordEncoder.encode(userRegisterDTO.getPassword());

        // 3. 组装实体并落库
        User user = new User();
        user.setUserName(userRegisterDTO.getUserName());
        user.setAccount(userRegisterDTO.getAccount());
        user.setPassword(encryptPassword);
        user.setUserRole(UserRoleEnum.COMMON.getCode());                // 默认普通用户
        user.setAccountStatus(AccountStatusEnum.NORMAL.getCode());      // 默认账号正常

        boolean saved = this.save(user);
        if (!saved) {
            throw new BusinessException(CommonErrorCode.SYSTEM_ERROR);
        }

        return user.getUserId();
    }

    @Override
    public UserInfoVO userLogin(UserLoginDTO userLoginDTO) {
        User user = userManager.queryUser(userLoginDTO.getAccount());
        if (Objects.isNull(user)) {
            throw new BusinessException(UserErrorCode.PASSWORD_WRONG);
        }
        if (!passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword())) {
            throw new BusinessException(UserErrorCode.PASSWORD_WRONG);
        }
        if (AccountStatusEnum.BAN.getCode().equals(user.getAccountStatus())) {
            throw new BusinessException(UserErrorCode.ACCOUNT_DISABLED);
        }
        return CopyUtil.copyObject(user, UserInfoVO.class);
    }

    @Override
    public UserInfoVO editUserInfo(EditUserInfoReqDTO editDTO, Long userId) {
        User user = new User();
        user.setUserId(userId);
        user.setUserName(editDTO.getUserName());
        user.setAvatar(editDTO.getAvatar());
        user.setEmail(editDTO.getEmail());
        user.setGender(editDTO.getGender());
        user.setPhone(editDTO.getPhone());
        if (!this.updateById(user)) {
            throw new BusinessException(UserErrorCode.USER_NOT_EXIST);
        }
        User updatedUser = this.getById(userId);
        if (Objects.isNull(updatedUser)) {
            throw new BusinessException(UserErrorCode.USER_NOT_EXIST);
        }
        return CopyUtil.copyObject(updatedUser, UserInfoVO.class);
    }
}




