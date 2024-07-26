package com.hanson.kafka.test.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.Future;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName KafkaMessageProducer
 * @Description TODO
 * @date 2024/7/23 19:51
 **/
public class KafkaMessageProducer {
    public static void main(String[] args) throws JsonProcessingException {
        // Kafka 配置
        Properties props = new Properties();
//        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "10.100.6.51:9092"); // Kafka 服务器地址
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "10.100.7.107:9092"); // Kafka 服务器地址
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        // 生成数据
        List<Map<String, Object>> dataList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Map<String, Object> data = new HashMap<>();
            data.put("accountid", "130014249");
            data.put("app_phone_manufacturer", "非APP");
            data.put("area", "080200");
            data.put("company_type_last", "");
            data.put("degree_last", "2");
            data.put("etl_time", "2024-06-14 17:12:47.163");
            data.put("expect_area_one", "");
            data.put("expect_func_one", "");
            data.put("expect_industry_one", "");
            data.put("expect_minsalary_one", "");
            data.put("expect_salary_one", "");
            data.put("last_login_app_date", "2021-04-09");
            data.put("oaid", "84123f1b-3c9c-4d0a-8ca6-e68d80c4054a");
            data.put("oaid_login_date", "");
            data.put("personas", "");
            data.put("pt1", "20240722");
            data.put("timeto_edu_exp_last", "2008-12-01");
            data.put("total_count", "19718402");
            data.put("uuid", "e19595e1f3d867ddfd14449f7661fbfc");
            data.put("uuid_login_date", "2021-04-09");
            data.put("workyear", "2015");
            dataList.add(data);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        // 发送数据到 Kafka
        for (Map<String, Object> data : dataList) {
            String jsonString = objectMapper.writeValueAsString(data); // 使用 Jackson 或其他工具将 Map 转换为 JSON 字符串
            ProducerRecord<String, String> record = new ProducerRecord<>("oaid_active_zhaohui", jsonString);
            try {
                Future<RecordMetadata> future = producer.send(record);
                RecordMetadata metadata = future.get();
                System.out.printf("Sent message to topic %s partition %d offset %d%n",
                        metadata.topic(), metadata.partition(), metadata.offset());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // 关闭生产者
        producer.close();
    }
}