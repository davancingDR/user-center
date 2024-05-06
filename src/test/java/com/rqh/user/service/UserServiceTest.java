package com.rqh.user.service;

import com.rqh.user.model.domain.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.assertTrue;

/**
 * 用户服务测试
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    public void testAddUser() {
        User user = new User();
        user.setUserName("testMan");
        user.setAccount("test123");
        user.setPassword("test123");
        user.setPhone("123");
        user.setEmail("456@qq.com");
        boolean save = userService.save(user);
        System.out.println(user);
        assertTrue(save);
    }
}