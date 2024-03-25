package com.hanson.transaction;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hanson.transaction.mapper")
public class SpringTransactionDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringTransactionDemoApplication.class, args);
    }

}
