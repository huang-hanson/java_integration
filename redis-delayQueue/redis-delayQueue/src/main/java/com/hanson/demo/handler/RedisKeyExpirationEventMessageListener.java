//package com.hanson.demo.handler;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.data.redis.connection.Message;
//import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
//import org.springframework.data.redis.listener.RedisMessageListenerContainer;
//import org.springframework.stereotype.Service;
//
///**
// * @author hanson.huang
// * @version V1.0
// * @ClassName RedisKeyExpirationEventMessageListener
// * @Description TODO
// * @date 2024/7/11 15:57
// **/
//@Service
//@Slf4j
//public class RedisKeyExpirationEventMessageListener extends KeyExpirationEventMessageListener {
//
//    public RedisKeyExpirationEventMessageListener(RedisMessageListenerContainer listenerContainer) {
//        super(listenerContainer);
//    }
//
//    @Override
//    public void onMessage(Message message, byte[] pattern) {
//        String expiredKey = message.toString();
//        log.info("监听到过期key:{}", expiredKey);
//    }
//}
