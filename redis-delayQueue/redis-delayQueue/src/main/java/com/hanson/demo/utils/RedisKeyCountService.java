package com.hanson.demo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName RedisKeyCountService
 * @Description TODO
 * @date 2024/7/23 17:41
 **/
@Service
public class RedisKeyCountService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public long countKeysByPattern(String pattern) {
        // 使用StringRedisTemplate的keys方法获取所有匹配的key
        // 注意：在生产环境中，如果key的数量很大，这可能会阻塞Redis服务器
        Set<String> keys = stringRedisTemplate.keys(pattern);
        // 返回匹配的key的数量
        return keys.size();
    }
}