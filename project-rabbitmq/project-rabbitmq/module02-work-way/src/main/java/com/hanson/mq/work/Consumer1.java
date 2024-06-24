package com.hanson.mq.work;

import com.hanson.mq.util.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author hanson
 * @date 2024/6/21 12:51
 */
public class Consumer1 {
    static final String QUEUE_NAME = "work_queue";

    public static void main(String[] args) throws Exception {

        Connection connection = ConnectionUtil.getConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME,true,false,false,null);

        Consumer consumer = new DefaultConsumer(channel){

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                System.out.println("Consumer1 body："+new String(body));

            }

        };

        channel.basicConsume(QUEUE_NAME,true,consumer);

    }

}
