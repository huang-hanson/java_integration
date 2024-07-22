package com.hansom.rabbitMQ.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName BaseRabbitConfig
 * @Description TODO
 * @date 2024/7/22 13:28
 **/
@EnableRabbit
@Slf4j
public class BaseRabbitConfig {
    public static final String EXCHANGE = "51job-activity";

    //生产者发送确认
    @Bean
    public RabbitTemplate.ConfirmCallback confirmCallback(){
        return new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                if (ack) {
                    //TODO
                    //System.out.println("发送者确认发送给mq成功！");
                }else {
                    log.error("发送者确认发送给mq失败，考虑重新发送：" + cause);
                    //System.out.println("发送者确认发送给mq失败，考虑重新发送：" + cause);
                }
            }
        };
    }
}