package com.hanson.mq.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.amqp.core.Message;
import com.rabbitmq.client.Channel;

import java.io.IOException;

/**
 * @author hanson
 * @date 2024/6/25 22:50
 */
@Component
@Slf4j
public class MyProcessor {

    @RabbitListener(queues = {"queue.cluster.test"})
    public void processNormalQueueMessage(String data, Message message, Channel channel)
            throws IOException {

        log.info("消费端：" + data);

        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    public static final String QUEUE_QUORUM_TEST = "queue.quorum.test";

    @RabbitListener(queues = {QUEUE_QUORUM_TEST})
    public void quorumMessageProcess(String data, Message message, Channel channel) throws IOException {
        log.info("消费端：" + data);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

}
