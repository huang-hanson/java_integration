package com.hanson.ioc.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * @author hanson
 * @date 2024/4/8 20:19
 */
@Scope("prototype")
@Controller  //将对象存储到Spring中
public class MyController1 {
    public void sayHi(){
        System.out.println("Hi, UserController...");
    }
}