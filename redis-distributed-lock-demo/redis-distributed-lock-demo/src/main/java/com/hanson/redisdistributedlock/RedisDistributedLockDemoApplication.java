package com.hanson.redisdistributedlock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RedisDistributedLockDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisDistributedLockDemoApplication.class, args);
    }

}
