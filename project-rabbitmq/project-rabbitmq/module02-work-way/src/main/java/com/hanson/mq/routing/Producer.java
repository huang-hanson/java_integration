package com.hanson.mq.routing;

import com.hanson.mq.util.ConnectionUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
/**
 * @author hanson
 * @date 2024/6/21 13:43
 */
public class Producer {
    public static void main(String[] args) throws Exception {

        Connection connection = ConnectionUtil.getConnection();

        Channel channel = connection.createChannel();

        String exchangeName = "test_direct";

        // 创建交换机
        channel.exchangeDeclare(exchangeName,BuiltinExchangeType.DIRECT,true,false,false,null);

        // 创建队列
        String queue1Name = "test_direct_queue1";
        String queue2Name = "test_direct_queue2";

        // 声明（创建）队列
        channel.queueDeclare(queue1Name,true,false,false,null);
        channel.queueDeclare(queue2Name,true,false,false,null);

        // 队列绑定交换机
        // 队列1绑定error
        channel.queueBind(queue1Name,exchangeName,"error");

        // 队列2绑定info error warning
        channel.queueBind(queue2Name,exchangeName,"info");
        channel.queueBind(queue2Name,exchangeName,"error");
        channel.queueBind(queue2Name,exchangeName,"warning");

        String message = "日志信息：张三调用了delete方法.错误了,日志级别warning";

        // 发送消息
        channel.basicPublish(exchangeName,"error",null,message.getBytes());
        System.out.println(message);

        // 释放资源
        channel.close();
        connection.close();

    }
}
