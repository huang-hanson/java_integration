package com.hanson.ioc.controller;

import com.hanson.ioc.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

/**
 * @author hanson
 * @date 2024/4/8 23:43
 */
@Controller
public class MyController6 {
    @Qualifier("user2") //指定bean的名称.
    @Autowired
    private User user;

    public void sayHi() {
        System.out.println("hi, UserController6...");
        System.out.println(user);
    }
}
