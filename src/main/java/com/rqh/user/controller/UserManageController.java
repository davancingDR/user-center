package com.rqh.user.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rqh.user.model.domain.dto.UserQueryReqDTO;
import com.rqh.user.model.domain.dto.UserRegisterDTO;
import com.rqh.user.model.domain.vo.UserInfoVO;
import com.rqh.user.model.result.BizResponse;
import com.rqh.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "后台用户管理")
@Slf4j
@RestController
@RequestMapping("/user/manage")
public class UserManageController {

    @Resource
    private UserService userService;

    // 实现用户的增删改查，可以查看用户列表，批量修改用户账号状态（封禁）

    @ApiOperation("分页查询用户列表")
    @PostMapping(value = "/page")
    public BizResponse<Page<UserInfoVO>> queryUserInfoPage(@RequestBody UserQueryReqDTO queryReqDTO) {
        Page<UserInfoVO> userInfoPage = userService.queryUserInfoPage(queryReqDTO);
        return BizResponse.success(userInfoPage);
    }

    @ApiOperation("添加用户")
    @PostMapping(value = "/add")
    public BizResponse<Long> addUser(UserRegisterDTO userRegisterDTO) {
        return BizResponse.success(userService.userRegister(userRegisterDTO));
    }

    @ApiOperation("删除用户")
    @PostMapping(value = "/delete")
    public BizResponse<Boolean> deleteUser(@RequestParam("userIds") List<Long> userIds) {
        return BizResponse.success(userService.removeBatchByIds(userIds));
    }

    // 管理员是否拥有修改用户信息的功能？不能，那管理员可以改什么？账号状态（是否封禁）、用户角色（赋予权限）【super 管理员权限】
    @ApiOperation("编辑账号状态")
    @PostMapping(value = "/change/status")
    public BizResponse<Boolean> updateStatus(List<Long> userIds, Integer status) {
        // 1.校验权限
        // 2.编辑账号状态
        return BizResponse.success();
    }
}
