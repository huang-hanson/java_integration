package com.hanson.kafka.test.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.*;

import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName KafkaProducerExample
 * @Description TODO
 * @date 2024/7/23 14:24
 **/
public class KafkaProducerExample {

    private static final String TOPIC = "oaid_active_zhaohui";
    private static final String BOOTSTRAP_SERVERS = "10.100.6.51:9092";//dev
//    private static final String BOOTSTRAP_SERVERS = "10.100.7.107:9092"; // test

    public static void main(String[] args) {
        sendOne();
//        sendTen();

    }

    private static void sendTen() {
        Properties props = new Properties();
        props.put("bootstrap.servers", BOOTSTRAP_SERVERS);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);

        List<Map<String, Object>> dataList = new ArrayList<>();
        for (int i = 0; i < 10; i++) { // 生成10条示例数据
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
            data.put("pt1", "20240724");
            data.put("timeto_edu_exp_last", "2008-12-01");
            data.put("total_count", "19718402");
            data.put("uuid", "e19595e1f3d867ddfd14449f7661fbfc");
            data.put("uuid_login_date", "2021-04-09");
            data.put("workyear", "2015");
            dataList.add(data);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            for (Map<String, Object> data : dataList) {
                String jsonString = objectMapper.writeValueAsString(data);
                ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC, jsonString);
                RecordMetadata metadata = producer.send(record).get();
                System.out.printf("Sent record with offset %d to partition %d%n", metadata.offset(), metadata.partition());
            }
        } catch (JsonProcessingException | ExecutionException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
    }

    private static void sendOne() {
        Properties props = new Properties();
        props.put("bootstrap.servers", BOOTSTRAP_SERVERS);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384); // 设置批量大小为16KB
        props.put(ProducerConfig.LINGER_MS_CONFIG, 10); // 设置linger.ms为10ms
        props.put(ProducerConfig.ACKS_CONFIG, "all");

        Producer<String, String> producer = new KafkaProducer<>(props);

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
        data.put("pt1", "20240724");
        data.put("timeto_edu_exp_last", "2008-12-01");
        data.put("total_count", "19718402");
        data.put("uuid", "e19595e1f3d867ddfd14449f7661fbfc");
        data.put("uuid_login_date", "2021-04-09");
        data.put("workyear", "2015");

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonString = objectMapper.writeValueAsString(data);
            ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC, jsonString);
            RecordMetadata metadata = producer.send(record).get();
            System.out.printf("Sent record with offset %d to partition %d%n", metadata.offset(), metadata.partition());
        } catch (JsonProcessingException | ExecutionException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
    }
}