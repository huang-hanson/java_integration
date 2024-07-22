package com.hansom.rabbitMQ.util;

import com.alibaba.fastjson.JSONObject;
import com.hansom.rabbitMQ.config.RabbitMQConfig;
import com.hansom.rabbitMQ.enums.MQEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName RabbitMQMessageUtils
 * @Description TODO
 * @date 2024/7/22 12:16
 **/
@Component
@Slf4j
public class RabbitMQMessageUtils {

    public static String formatJavaMessage(String msg){
        JSONObject message = new JSONObject();
        message.put("messageBody",msg);
        message.put("messageCreateTime", System.currentTimeMillis());
        message.put("messageKey", DigestUtils.md5Hex(msg+System.currentTimeMillis()));
        return message.toJSONString();
    }

    public static boolean pushMessages(RabbitTemplate rabbitTemplate, String msg, int retryTimes, String exchange, String routingKey) {
        if (retryTimes > 0) {
            RetryTemplate retryTemplate = new RetryTemplate();
            retryTemplate.setRetryPolicy(new SimpleRetryPolicy(retryTimes));
            rabbitTemplate.setRetryTemplate(retryTemplate);
        }
        rabbitTemplate.convertAndSend(exchange, routingKey, msg);
        return true;
    }

    private static boolean testMq(RabbitTemplate rabbitTemplate, String msg, int retryTimes) {
        return pushMessages(rabbitTemplate, formatJavaMessage(msg), retryTimes, RabbitMQConfig.EXCHANGE, RabbitMQConfig.TEST_ROUTING_KEY);
    }

    /**
     * 发送消息通用方法
     */
    public static boolean MQProducter(RabbitTemplate rabbitTemplate, MQEnum mqEnum, String msg, int retryTimes) {
        try {
            switch (mqEnum) {
                case TEST_MQ:
                    return testMq(rabbitTemplate, msg, retryTimes);

                default:
                    return false;
            }
        }catch (Exception e){
            log.error("{} producer is error,msg:{},error msg:{}", mqEnum, msg, e.getMessage());
            return false;
        }
    }
}