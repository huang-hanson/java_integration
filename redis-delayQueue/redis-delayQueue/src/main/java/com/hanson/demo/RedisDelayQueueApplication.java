package com.hanson.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RedisDelayQueueApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisDelayQueueApplication.class, args);
    }

}
