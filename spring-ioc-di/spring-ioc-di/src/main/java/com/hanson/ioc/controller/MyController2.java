package com.hanson.ioc.controller;

import com.hanson.ioc.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author hanson
 * @date 2024/4/8 23:43
 */
@Controller //将对象存储到Spring中
public class MyController2 {
    //注入方法1: 属性注入
    @Autowired
    private MyService myService;

    public void sayHi() {
        System.out.println("Hi, UserController...");
        myService.sayHi();
    }


}
