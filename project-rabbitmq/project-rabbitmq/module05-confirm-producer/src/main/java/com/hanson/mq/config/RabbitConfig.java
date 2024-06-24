package com.hanson.mq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author hanson
 * @date 2024/6/21 17:42
 */
@Slf4j
@Configuration
public class RabbitConfig implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnsCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void initRabbitTemplate() {
        this.rabbitTemplate.setConfirmCallback(this);
        this.rabbitTemplate.setReturnsCallback(this);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        // 消息发送到交换机成功或者失败时调用这个方法
        log.info("confirm()回调函数打印 CorrelationData:" + correlationData);
        log.info("confirm()回调函数打印 ack:" + ack);
        log.info("confirm()回调函数打印 cause:" + cause);
    }

    @Override
    public void returnedMessage(ReturnedMessage returned) {
        // 发送到队列失败才调用这个方法
        log.info("消息主体: " + new String(returned.getMessage().getBody()));
        log.info("应答码: " + returned.getReplyCode());
        log.info("描述：" + returned.getReplyText());
        log.info("消息使用的交换器 exchange : " + returned.getExchange());
        log.info("消息使用的路由键 routing : " + returned.getRoutingKey());
    }
}
