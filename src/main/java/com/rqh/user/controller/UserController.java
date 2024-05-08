package com.rqh.user.controller;

import com.rqh.user.model.domain.dto.EditPasswordReqDTO;
import com.rqh.user.model.domain.dto.EditUserInfoReqDTO;
import com.rqh.user.model.domain.dto.UserLoginDTO;
import com.rqh.user.model.domain.dto.UserRegisterDTO;
import com.rqh.user.model.domain.vo.UserInfoVO;
import com.rqh.user.model.result.BizResponse;
import com.rqh.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "用户接口")
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public BizResponse<Long> userRegister(UserRegisterDTO userRegisterDTO) {
        return BizResponse.success(userService.userRegister(userRegisterDTO));
    }

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public BizResponse<Boolean> userLogin(UserLoginDTO loginDTO) {
        return BizResponse.success();
    }

    @ApiOperation("查看用户个人信息")
    @PostMapping(value = "/detail")
    public BizResponse<UserInfoVO> getUserInfo(Long userId) {
        return BizResponse.success();
    }

    @ApiOperation("修改用户个人信息")
    @PostMapping(value = "/edit/info")
    public BizResponse<Boolean> updateUser(EditUserInfoReqDTO editDTO) {
        return BizResponse.success();
    }

    @ApiOperation("修改密码")
    @PostMapping(value = "/edit/password")
    public BizResponse<Boolean> updatePassword(EditPasswordReqDTO editDTO) {
        // 检验原密码是否正确
        // 新密码和验证密码是否一致
        // 修改密码
        return BizResponse.success();
    }

    @ApiOperation("注销账户")
    @PostMapping(value = "/logout")
    public BizResponse<Boolean> logout(Long userId) {
        return BizResponse.success();
    }
}
