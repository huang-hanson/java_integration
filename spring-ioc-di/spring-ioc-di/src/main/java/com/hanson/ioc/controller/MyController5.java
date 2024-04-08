package com.hanson.ioc.controller;

import com.hanson.ioc.domain.User;
import com.hanson.ioc.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author hanson
 * @date 2024/4/8 23:43
 */
@Controller //将对象存储到Spring中
public class MyController5 {
    @Autowired
    private User user;

    public void sayHi() {
        System.out.println("hi, UserController4...");
        System.out.println(user);
    }
}
