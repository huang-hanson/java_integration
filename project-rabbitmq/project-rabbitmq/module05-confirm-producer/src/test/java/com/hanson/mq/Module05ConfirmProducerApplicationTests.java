package com.hanson.mq;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
class Module05ConfirmProducerApplicationTests {

    public static final String EXCHANGE_DIRECT = "exchange.direct.order";

    public static final String EXCHANGE_TIMEOUT = "exchange.test.timeout";

    public static final String EXCHANGE_NORMAL = "exchange.normal.video";
    public static final String EXCHANGE_DELAY = "exchange.delay.happy";
    public static final String EXCHANGE_DEAD_LETTER = "exchange.dead.letter.video";

    public static final String ROUTING_KEY_NORMAL = "routing.key.normal.video";
    public static final String ROUTING_KEY_DELAY = "queue.delay.video";
    public static final String ROUTING_KEY_DEAD_LETTER = "routing.key.dead.letter.video";
    public static final String ROUTING_KEY = "order";

    public static final String ROUTING_KEY_TIMEOUT = "routing.key.test.timeout";

    public static final String EXCHANGE_PRIORITY = "exchange.test.priority";
    public static final String ROUTING_KEY_PRIORITY = "routing.key.test.priority";


    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void test01SendMessage() {
        rabbitTemplate.convertAndSend(EXCHANGE_DIRECT + "~", ROUTING_KEY, "Message Test Confirm~~~");
    }

    @Test
    public void test02SendMessage() {
        for (int i = 0; i < 100; i++) {
            rabbitTemplate.convertAndSend(EXCHANGE_DIRECT, ROUTING_KEY, "Test Prefetch " + i);
        }
    }

    @Test
    public void test03SendMessage() {
        for (int i = 0; i < 100; i++) {
            rabbitTemplate.convertAndSend(EXCHANGE_TIMEOUT, ROUTING_KEY_TIMEOUT, "Test timeout " + i);
        }
    }

    /**
     * 添加消息处理对象来添加过期时间
     */
    @Test
    public void test04SendMessage() {

        // 创建消息后部署处理器对象
        MessagePostProcessor postProcessor = message -> {

            // 设置消息的过期时间
            message.getMessageProperties().setExpiration("7000");

            return message;
        };

        rabbitTemplate.convertAndSend(EXCHANGE_TIMEOUT, ROUTING_KEY_TIMEOUT, "Test timeout ", postProcessor);
    }

    @Test
    public void test05SendMessage() {
        rabbitTemplate.convertAndSend(EXCHANGE_NORMAL, ROUTING_KEY_NORMAL, "Message Test Confirm~~~");
    }

    @Test
    public void testSendMultiMessage() {
        for (int i = 0; i < 20; i++) {
            rabbitTemplate.convertAndSend(
                    EXCHANGE_NORMAL,
                    ROUTING_KEY_NORMAL,
                    "测试死信情况2：消息数量超过队列的最大容量" + i);
        }
    }

    @Test
    public void test06SendMessage() {
        rabbitTemplate.convertAndSend(
                EXCHANGE_DELAY,
                ROUTING_KEY_DELAY,
                "测试基于插件的延迟消息 [" + new SimpleDateFormat("hh:mm:ss").format(new Date()) + "]",
                messageProcessor -> {

                    // 设置延迟时间：以毫秒为单位
                    messageProcessor.getMessageProperties().setHeader("x-delay", "10000");

                    return messageProcessor;
                });
    }

    @Test
    public void test07SendMessage() {
        rabbitTemplate.convertAndSend(
                EXCHANGE_PRIORITY,
                ROUTING_KEY_PRIORITY,
                "message test priority 3",message -> {
            // 消息本身的优先数值
            // 切记：不要超过x-max-priority:10


//            message.getMessageProperties().setPriority(1);
//            message.getMessageProperties().setPriority(2);
            message.getMessageProperties().setPriority(3);
            return message;
        });
    }
}
