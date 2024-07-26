package com.hanson.demo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName RedisAddService
 * @Description TODO
 * @date 2024/7/23 18:27
 **/
@Service
public class RedisAddService {

    @Resource(name = "stringRedisTemplate")
    private StringRedisTemplate stringRedisTemplate;

    public void setKeysWithOne() {
        long startTime = System.currentTimeMillis();

        // 使用普通的单次操作
        for (int i = 0; i < 1000000; i++) {
            String key = "test_key:" + i;
            stringRedisTemplate.opsForValue().set(key, "1", 2, TimeUnit.DAYS);
            System.out.println("完成第" + i + "次写入");
        }

        long endTime = System.currentTimeMillis();
        System.out.println("完成1000万次写入，耗时：" + (endTime - startTime) + "毫秒");
    }
}