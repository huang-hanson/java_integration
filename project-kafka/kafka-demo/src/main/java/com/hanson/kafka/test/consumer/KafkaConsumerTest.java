package com.hanson.kafka.test.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


/**
 * @author hanson
 * @date 2024/6/27 13:05
 */
public class KafkaConsumerTest {

    public static void main(String[] args) {

        // TODO 创建配置对象
        Map<String, Object> consumerConfig = new HashMap<>();
        consumerConfig.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        consumerConfig.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerConfig.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerConfig.put(ConsumerConfig.GROUP_ID_CONFIG, "hanson");

        // TODO 创建消费者对象
        KafkaConsumer<String, String> consumer = new KafkaConsumer(consumerConfig);

        // TODO 订阅主题
        consumer.subscribe(Collections.singletonList("test"));

        // TODO 从Kafka的主题中获取数据
        //    消费者从kafka中拉取数据
        while (true){
            final ConsumerRecords<String, String> datas = consumer.poll(100);

            for (ConsumerRecord<String, String> data : datas) {
                System.out.println(data);
            }
        }


        // TODO 关闭消费者对象
//        consumer.close();
    }
}
