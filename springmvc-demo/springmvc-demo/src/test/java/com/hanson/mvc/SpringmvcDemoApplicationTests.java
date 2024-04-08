package com.hanson.mvc;

import com.hanson.mvc.domain.Userinfo;
import com.hanson.mvc.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringmvcDemoApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void login() {
        String username = "admin";
        String password = "' or 1 = '1";
        Userinfo userinfo = userMapper.login(username, password);
        System.out.println("登录状态：" + (userinfo == null ? "失败" : "成功"));
    }
}
