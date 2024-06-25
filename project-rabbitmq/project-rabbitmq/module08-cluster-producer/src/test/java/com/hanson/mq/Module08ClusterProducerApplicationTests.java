package com.hanson.mq;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class Module08ClusterProducerApplicationTests {

    @Resource
    private RabbitTemplate rabbitTemplate;

    public static final String EXCHANGE_CLUSTER_TEST = "exchange.cluster.test";
    public static final String ROUTING_KEY_CLUSTER_TEST = "routing.key.cluster.test";

    @Test
    public void testSendMessage() {
        rabbitTemplate.convertAndSend(EXCHANGE_CLUSTER_TEST, ROUTING_KEY_CLUSTER_TEST, "message test cluster~~~");
    }


    public static final String EXCHANGE_QUORUM_TEST = "exchange.quorum.test";
    public static final String ROUTING_KEY_QUORUM_TEST = "routing.key.quorum.test";

    @Test
    public void testSendMessageToQuorum() {
        rabbitTemplate.convertAndSend(EXCHANGE_QUORUM_TEST, ROUTING_KEY_QUORUM_TEST, "message test quorum ~~~");
    }

}
