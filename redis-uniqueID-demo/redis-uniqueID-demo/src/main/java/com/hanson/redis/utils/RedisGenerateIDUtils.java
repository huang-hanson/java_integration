package com.hanson.redis.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.swing.plaf.synth.SynthTextAreaUI;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

@Component
public class RedisGenerateIDUtils {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    // key前缀
    private String PREFIX = "GENERATEID:";

    /**
     * 获取全局唯一ID
     * @param key 业务标识key
     */
    public String generateId(String key) {
        // 获取对应业务自增序列
        Long incr = getIncr(key);
        // 组装最后的结果，这里可以根据需要自己定义，这里是按照业务标识key+当前时间戳+自增序列进行组装
        String resultID = key + System.currentTimeMillis() + "-" + incr;
        return resultID;
    }

    /**
     * 获取对应业务自增序列
     */
    private Long getIncr(String key) {
        String cacheKey = getCacheKey(key);
        Long increment = 0L;
        // 判断Redis中是否存在这个自增序列，如果不存在添加一个序列并且设置一个过期时间
        if (!redisTemplate.hasKey(cacheKey)) {
            // 这里存在线程安全问题，需要加分布式锁，这里做简单实现
            String lockKey = cacheKey + "_LOCK";
            // 设置分布式锁
            boolean lock = redisTemplate.opsForValue().setIfAbsent(lockKey, 1, 30, TimeUnit.SECONDS);
            if (!lock) {
                // 如果没有拿到锁进行自旋
                return getIncr(key);
            }
            increment = redisTemplate.opsForValue().increment(cacheKey);
            // 我这里设置24小时，可以根据实际情况设置当前时间到当天结束时间的插值
            redisTemplate.expire(cacheKey, 24, TimeUnit.HOURS);

            // 释放锁
            redisTemplate.delete(lockKey);
        } else {
            increment = redisTemplate.opsForValue().increment(cacheKey);
        }

        return increment;
    }

    /**
     * 组装缓存key
     */
    private String getCacheKey(String key) {
        return PREFIX + key + ":" + getYYYYMMDD();
    }

    /**
     * 获取当前YYYYMMDD格式年月日
     */
    private String getYYYYMMDD() {
        LocalDate currentDate = LocalDate.now();
        int year = currentDate.getYear();
        int month = currentDate.getMonthValue();
        int day = currentDate.getDayOfMonth();
        return "" + year + month + day;
    }
}
