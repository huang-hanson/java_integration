package com.hanson.mq.topic;

import com.hanson.mq.util.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author hanson
 * @date 2024/6/21 15:03
 */
public class Consumer2 {

    public static void main(String[] args) throws Exception {

        Connection connection = ConnectionUtil.getConnection();

        Channel channel = connection.createChannel();

        String QUEUE_NAME = "test_topic_queue2";

        channel.queueDeclare(QUEUE_NAME,true,false,false,null);

        Consumer consumer = new DefaultConsumer(channel){

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                System.out.println("body："+new String(body));

            }

        };

        channel.basicConsume(QUEUE_NAME,true,consumer);

    }
}
