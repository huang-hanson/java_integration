package com.hanson.cache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@EnableCaching
@SpringBootApplication
@MapperScan("com.hanson.cache.mapper")
public class SpringCahceDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCahceDemoApplication.class, args);
    }

}
