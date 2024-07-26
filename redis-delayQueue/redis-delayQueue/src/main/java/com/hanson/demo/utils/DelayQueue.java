//package com.hanson.demo.utils;
//
//import com.alibaba.fastjson.JSONObject;
//import com.hanson.demo.entity.DelayMessage;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Component;
//
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//import java.util.*;
//
///**
// * @author hanson.huang
// * @version V1.0
// * @ClassName DelayQueue
// * @Description TODO
// * @date 2024/7/11 16:53
// **/
//@Component
//public class DelayQueue {
//
//    private static final String KEY = "delay_queue:" + getHostAddress();
//
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    /**
//     * 添加消息到延时队列中
//     */
//    public void put(DelayMessage message) {
//        redisTemplate.opsForZSet().add(KEY, message, message.getExpireTime());
//    }
//
//    /**
//     * 从延时队列中删除消息
//     */
//    public Long remove(DelayMessage message) {
//        Long remove = redisTemplate.opsForZSet().remove(KEY, message);
//        return remove;
//    }
//
//    /**
//     * 获取延时队列中已到期的消息
//     */
//    public List<DelayMessage> getExpiredMessages() {
//        // 1. 获取到开始时间
//        long minScore = 0;
//        // 2. 获取到结束时间
//        long maxScore = System.currentTimeMillis();
//        // 3. 获取到指定范围区间的数据列表
//        Set<Object> messages = redisTemplate.opsForZSet().rangeByScore(KEY, minScore, maxScore);
//        if (messages == null || messages.isEmpty()) {
//            return Collections.emptyList();
//        }
//
//        // 4. 把对像进行封装，返回
//        List<DelayMessage> result = new ArrayList<>();
//        for (Object message : messages) {
//            DelayMessage delayMessage = JSONObject.parseObject(JSONObject.toJSONString(message), DelayMessage.class);
//            result.add(delayMessage);
//        }
//        return result;
//    }
//
//    /**
//     * 获取地址(服务器的内网地址)(内网ip)
//     *
//     * @return
//     */
//    public static String getHostAddress() {
//        InetAddress localHost = null;
//        try {
//            localHost = InetAddress.getLocalHost();
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        }
//        return localHost.getHostAddress();
//    }
//}