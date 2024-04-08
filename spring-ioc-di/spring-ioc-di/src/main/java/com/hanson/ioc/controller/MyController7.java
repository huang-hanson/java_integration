package com.hanson.ioc.controller;

import com.hanson.ioc.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * @author hanson
 * @date 2024/4/8 23:43
 */
@Controller
public class MyController7 {
    @Resource(name = "user2") //指定bean的名称.
    @Autowired
    private User user;

    public void sayHi() {
        System.out.println("hi, UserController7...");
        System.out.println(user);
    }
}
