package com.hanson.ioc.controller;

import com.hanson.ioc.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author hanson
 * @date 2024/4/8 23:43
 */
@Controller //将对象存储到Spring中
public class MyController3 {
    private MyService myService;

    //注入方法2: 构造方法注入
    @Autowired
    public MyController3(MyService myService) {
        this.myService = myService;
    }

    public void sayHi() {
        System.out.println("Hi, UserController...");
        myService.sayHi();
    }


}
