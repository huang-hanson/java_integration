package com.hanson.mvc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hanson.mvc.mapper")
public class SpringmvcDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringmvcDemoApplication.class, args);
    }

}
