package com.rqh.user.modules.user.controller;

import com.rqh.user.common.response.Result;
import com.rqh.user.modules.user.model.dto.EditPasswordReqDTO;
import com.rqh.user.modules.user.model.dto.EditUserInfoReqDTO;
import com.rqh.user.modules.user.model.dto.UserLoginDTO;
import com.rqh.user.modules.user.model.dto.UserRegisterDTO;
import com.rqh.user.modules.user.model.vo.UserInfoVO;
import com.rqh.user.modules.user.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;

@Tag(name = "用户接口")
@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserService userService;

    @Operation(summary = "用户注册")
    @PostMapping("/register")
    public Result<Long> userRegister(@Valid UserRegisterDTO userRegisterDTO) {
        return Result.success(userService.userRegister(userRegisterDTO));
    }

    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public Result<Boolean> userLogin(UserLoginDTO loginDTO) {
        return Result.success();
    }

    @Operation(summary = "查看用户个人信息")
    @PostMapping(value = "/detail")
    public Result<UserInfoVO> getUserInfo(Long userId) {
        return Result.success();
    }

    @Operation(summary = "修改用户个人信息")
    @PostMapping(value = "/edit/info")
    public Result<Boolean> updateUser(EditUserInfoReqDTO editDTO) {
        return Result.success();
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
    @PostMapping(value = "/logout")
    public Result<Boolean> logout(Long userId) {
        return Result.success();
    }
}
