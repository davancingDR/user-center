package com.rqh.user.modules.user.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rqh.user.common.response.Result;
import com.rqh.user.modules.user.model.dto.UserQueryReqDTO;
import com.rqh.user.modules.user.model.dto.UserRegisterDTO;
import com.rqh.user.modules.user.model.vo.UserInfoVO;
import com.rqh.user.modules.user.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.List;

@Tag(name = "后台用户管理")
@Slf4j
@RestController
@RequestMapping("/api/admin/user")
public class UserAdminController {

    @Resource
    private UserService userService;

    // 实现用户的增删改查，可以查看用户列表，批量修改用户账号状态（封禁）

    @Operation(summary = "分页查询用户列表")
    @PostMapping(value = "/page")
    public Result<Page<UserInfoVO>> queryUserInfoPage(@RequestBody UserQueryReqDTO queryReqDTO) {
        Page<UserInfoVO> userInfoPage = userService.queryUserInfoPage(queryReqDTO);
        return Result.success(userInfoPage);
    }

    @Operation(summary = "添加用户")
    @PostMapping(value = "/add")
    public Result<Long> addUser(@RequestBody @Valid UserRegisterDTO userRegisterDTO) {
        return Result.success(userService.userRegister(userRegisterDTO));
    }

    @Operation(summary = "删除用户")
    @PostMapping(value = "/delete")
    public Result<Boolean> deleteUser(@RequestParam("userIds") List<Long> userIds) {
        return Result.success(userService.removeBatchByIds(userIds));
    }

    // 管理员是否拥有修改用户信息的功能？不能，那管理员可以改什么？账号状态（是否封禁）、用户角色（赋予权限）【super 管理员权限】
    @Operation(summary = "编辑账号状态")
    @PostMapping(value = "/change/status")
    public Result<Boolean> updateStatus(List<Long> userIds, Integer status) {
        // 1.校验权限
        // 2.编辑账号状态
        return Result.success();
    }
}
