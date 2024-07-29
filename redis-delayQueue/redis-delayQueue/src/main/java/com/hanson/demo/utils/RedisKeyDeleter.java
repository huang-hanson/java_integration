package com.hanson.demo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName DeleteKeyService
 * @Description TODO
 * @date 2024/7/23 19:32
 **/
@Component
public class RedisKeyDeleter {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void deleteKeysByPattern(String pattern) {
        // 注意：在生产环境中，使用keys命令可能会阻塞Redis服务器
        Set<String> keys = stringRedisTemplate.keys(pattern);
        if (!keys.isEmpty()) {
            stringRedisTemplate.delete(keys);
        }
    }

    // 调用方法
    public void deleteTestKeys() {
        deleteKeysByPattern("test_key:*");
    }
}