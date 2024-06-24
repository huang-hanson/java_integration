package com.hanson.mq.simple;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author hanson
 * @date 2024/6/21 12:30
 */
public class Consumer {
    public static void main(String[] args) throws Exception {

        // 1.创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();

        // 2. 设置参数
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setVirtualHost("/");
        factory.setUsername("guest");
        factory.setPassword("123456");

        // 3. 创建连接 Connection
        Connection connection = factory.newConnection();

        // 4. 创建Channel
        Channel channel = connection.createChannel();

        // 5. 创建队列
        // 如果没有一个名字叫simple_queue的队列，则会创建该队列，如果有则不会创建
        // 参数1. queue：队列名称
        // 参数2. durable：是否持久化。如果持久化，则当MQ重启之后还在
        // 参数3. exclusive：是否独占。
        // 参数4. autoDelete：是否自动删除。当没有Consumer时，自动删除掉
        // 参数5. arguments：其它参数。
        channel.queueDeclare("simple_queue",true,false,false,null);

        // 接收消息
        DefaultConsumer consumer = new DefaultConsumer(channel){

            // 回调方法,当收到消息后，会自动执行该方法
            // 参数1. consumerTag：标识
            // 参数2. envelope：获取一些信息，交换机，路由key...
            // 参数3. properties：配置信息
            // 参数4. body：数据
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                System.out.println("consumerTag："+consumerTag);
                System.out.println("Exchange："+envelope.getExchange());
                System.out.println("RoutingKey："+envelope.getRoutingKey());
                System.out.println("properties："+properties);
                System.out.println("body："+new String(body));

            }

        };

        // 参数1. queue：队列名称
        // 参数2. autoAck：是否自动确认，类似咱们发短信，发送成功会收到一个确认消息
        // 参数3. callback：回调对象
        // 消费者类似一个监听程序，主要是用来监听消息
        channel.basicConsume("simple_queue",true,consumer);

    }

}
