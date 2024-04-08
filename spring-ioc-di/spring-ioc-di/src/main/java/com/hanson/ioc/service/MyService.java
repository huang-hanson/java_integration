package com.hanson.ioc.service;

import org.springframework.stereotype.Service;

/**
 * @author hanson
 * @date 2024/4/8 23:43
 */
@Service
public class MyService {
    public void sayHi() {
        System.out.println("Hi, MyService");
    }
}
