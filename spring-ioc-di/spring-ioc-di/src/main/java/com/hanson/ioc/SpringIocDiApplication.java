package com.hanson.ioc;

import com.hanson.ioc.controller.*;
import com.hanson.ioc.domain.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;


//@ComponentScan(value = "com.hanson.ioc.service")
@SpringBootApplication
public class SpringIocDiApplication {

    public static void main(String[] args) {
        //获取Spring上下文对象
        ApplicationContext context = SpringApplication.run(SpringIocDiApplication.class, args);

        for (int i = 0; i < 10; i++) {
            MyController1 myController1 = context.getBean(MyController1.class);
            myController1.sayHi();
            System.out.println(myController1);
        }

        // 方法名
//        User user1 = (User) context.getBean("user1");
//        User user2 = (User) context.getBean("user2");
//        System.out.println(user1);
//        System.out.println(user2);

        // 起别名
//        User user = context.getBean("u1",User.class);
//        System.out.println(user);

//        //根据bean类型, 从Spring上下文中获取对象.
//        MyController myController1 = context.getBean(MyController.class);
//        //根据bean名称, 从Spring上下文中获取对象
//        MyController myController2 = (MyController) context.getBean("myController");
//        //根据bean名称+对象, 从Spring上下文中获取对象
//        MyController myController3 = context.getBean("myController", MyController.class);
//        //使用对象
//        System.out.println(myController1);
//        System.out.println(myController2);
//        System.out.println(myController3);
    }

}
