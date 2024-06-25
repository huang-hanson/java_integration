package com.hanson.mq.stream;

import com.rabbitmq.stream.Environment;

/**
 * @author hanson
 * @date 2024/6/25 23:41
 */
public class TestConsumer {
    public static void main(String[] args) {
        Environment environment = Environment.builder()
                .host("192.168.47.100")
                .port(33333)
                .username("hanson")
                .password("123456")
                .build();

        environment.consumerBuilder()
                .stream("stream.hanson.test")
                .name("stream.hanson.test.consumer")
                .autoTrackingStrategy()
                .builder()
                .messageHandler((offset, message) -> {
                    byte[] bodyAsBinary = message.getBodyAsBinary();
                    String messageContent = new String(bodyAsBinary);
                    System.out.println("[消费者端]messageContent = " + messageContent + " Offset=" + offset.offset());
                })
                .build();
    }
}
