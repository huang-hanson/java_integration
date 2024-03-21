package com.hanson.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hanson.test.mapper")
public class UnittestDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(UnittestDemoApplication.class, args);
    }

}
