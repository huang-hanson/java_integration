package com.hanson.demo;

import com.hanson.demo.utils.RedisAddService;
import com.hanson.demo.utils.RedisKeyCountService;
import com.hanson.demo.utils.RedisKeyDeleter;
import com.hanson.demo.utils.RedisKeyScannerService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName TestApplicationTests
 * @Description TODO
 * @date 2024/7/23 17:45
 **/
@SpringBootTest
class TestApplicationTests {

    @Resource
    private RedisKeyCountService redisKeyCountService;

    @Resource
    private RedisKeyScannerService redisKeyScannerService;

    @Resource
    private RedisAddService redisAddService;

    @Resource
    private RedisKeyDeleter redisKeyDeleter;

    @Test
    void keyCountTest() {
        long startTime = System.currentTimeMillis();
//        long count = redisKeyCountService.countKeysByPattern("string_job_info_*");
        long count = redisKeyCountService.countKeysByPattern("test_key:*");
        System.out.println("count: " + count);
        System.out.println("startTime " + startTime);
        System.out.println("endTime " + System.currentTimeMillis());
        System.out.println("cost: " + (System.currentTimeMillis() - startTime));

    }

    @Test
    void keyScannerTest() {
        long startTime = System.currentTimeMillis();
//        long count = redisKeyScannerService.countKeysByPattern("string_job_info_*");
//        long count = redisKeyScannerService.countKeysByPattern("resume_info_list_20221025*");
        long count = redisKeyScannerService.countKeysByPattern("test_key:*");
        System.out.println("count: " + count);
        System.out.println("startTime " + startTime);
        System.out.println("endTime " + System.currentTimeMillis());
        System.out.println("cost: " + (System.currentTimeMillis() - startTime));

    }

    @Test
    void add1000RedisDataTest() {
        redisAddService.setKeysWithOne();
    }

    @Test
    void deleteKey() {
        redisKeyDeleter.deleteTestKeys();
    }

}