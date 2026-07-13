package com.rqh.user.modules.user.controller;

import com.rqh.user.common.errorcode.CommonErrorCode;
import com.rqh.user.common.exception.BusinessException;
import com.rqh.user.common.response.Result;
import com.rqh.user.modules.user.model.dto.EditPasswordReqDTO;
import com.rqh.user.modules.user.model.dto.EditUserInfoReqDTO;
import com.rqh.user.modules.user.model.dto.UserLoginDTO;
import com.rqh.user.modules.user.model.dto.UserRegisterDTO;
import com.rqh.user.modules.user.model.vo.UserInfoVO;
import com.rqh.user.modules.user.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;

import java.util.Objects;

@Tag(name = "用户接口")
@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {

    private static final String LOGIN_USER = "loginUser";

    @Resource
    private UserService userService;

    @Operation(summary = "用户注册")
    @PostMapping("/register")
    public Result<Long> userRegister(@RequestBody @Valid UserRegisterDTO userRegisterDTO) {
        return Result.success(userService.userRegister(userRegisterDTO));
    }

    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public Result<UserInfoVO> userLogin(@RequestBody @Valid UserLoginDTO loginDTO,
                                        HttpServletRequest request) {
        UserInfoVO userInfo = userService.userLogin(loginDTO);
        request.getSession().setAttribute(LOGIN_USER, userInfo);
        return Result.success(userInfo);
    }

    @Operation(summary = "用户登出")
    @PostMapping("/logout")
    public Result<Boolean> userLogout(HttpServletRequest request) {
        request.getSession().removeAttribute(LOGIN_USER);
        return Result.success(Boolean.TRUE);
    }

    @Operation(summary = "获取当前登录用户信息")
    @PostMapping(value = "/detail")
    public Result<UserInfoVO> getUserInfo(HttpServletRequest request) {
        UserInfoVO userInfo = (UserInfoVO) request.getSession().getAttribute(LOGIN_USER);
        if (Objects.isNull(userInfo)) {
            throw new BusinessException(CommonErrorCode.UNAUTHORIZED);
        }
        return Result.success(userInfo);
    }

    @Operation(summary = "修改用户个人信息")
    @PostMapping(value = "/edit/info")
    public Result<UserInfoVO> updateUser(@RequestBody @Valid EditUserInfoReqDTO editDTO,
                                      HttpServletRequest request) {
        UserInfoVO loginUser = (UserInfoVO) request.getSession().getAttribute(LOGIN_USER);
        if (Objects.isNull(loginUser)) {
            throw new BusinessException(CommonErrorCode.UNAUTHORIZED);
        }
        UserInfoVO updatedUser = userService.editUserInfo(editDTO, loginUser.getUserId());
        request.getSession().setAttribute(LOGIN_USER, updatedUser);
        return Result.success(updatedUser);
    }

    @Operation(summary = "修改密码")
    @PostMapping(value = "/edit/password")
    public Result<Boolean> updatePassword(EditPasswordReqDTO editDTO) {
        // 检验原密码是否正确
        // 新密码和验证密码是否一致
        // 修改密码
        return Result.success();
    }

    @Operation(summary = "注销账户")
    @PostMapping(value = "/cancel")
    public Result<Boolean> logout(Long userId) {
        return Result.success();
    }
}
