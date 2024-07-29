package com.hansom.rabbitMQ.config;

import com.hansom.rabbitMQ.entity.MqFailMessageQuery;
import com.hansom.rabbitMQ.service.MqFailMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName BaseRabbitMQConsumer
 * @Description TODO
 * @date 2024/7/22 13:58
 **/
@Component
@Slf4j
public class BaseRabbitMQConsumer {

    @Resource(name = "stringRedisTemplate")
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private MqFailMessageService mqFailMessageService;

    private final String redisPrefix = "con_err_num";

    public int getNumber(String queueName, String messageKey) {
        String result = stringRedisTemplate.opsForValue().get(redisPrefix + queueName + "_" + messageKey);
        int num = 0;
        if (result != null) {
            num = Integer.parseInt(result);
        }
        return num;
    }

    public void addNumber(String queueName, String messageKey) {
        stringRedisTemplate.opsForValue().increment(redisPrefix + queueName + "_" + messageKey, 1);
        stringRedisTemplate.expire(redisPrefix + queueName + "_" + messageKey, 5, TimeUnit.MINUTES);
    }

    public void insertFailMessage(MqFailMessageQuery mqFailMessageQuery) {
        try {
            mqFailMessageService.insertMqFailMessage(mqFailMessageQuery);
        } catch (Exception e) {
            log.error("consumer insert fail msg error,data:{},error msg:{}", mqFailMessageQuery, e.getMessage());
        }
    }
}