package com.hanson.kafka.test.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.HashMap;

/**
 * @author hanson
 * @date 2024/6/27 12:55
 */
public class KafkaProducerPartitionerTest {

    public static void main(String[] args) {

        // TODO 创建配置对象
        HashMap<String, Object> configMap = new HashMap<>();
        configMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        // TODO 对生产的数据K，V进行序列化操作
        configMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configMap.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, MyKafkaPartitioner.class.getName());

        // TODO 创建生产者对象
        //  生产者对象需要设置泛型
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(configMap);


        // TODO 创建数据
        //   构建数据据时，需要传入三个参数
        //     第一个参数表示主题的名称
        //     第二个表数据据的key，
        //     第三个表示数据的value
        for (int i = 0; i < 10; i++) {
            ProducerRecord<String, String> record = new ProducerRecord<String, String>(
                    "test", "key" + i, "value" + i
            );

            // TODO 通过生产者对象将数据发送到kafka
            producer.send(record);
        }




        // TODO 关闭生产者对象
        producer.close();
    }
}
