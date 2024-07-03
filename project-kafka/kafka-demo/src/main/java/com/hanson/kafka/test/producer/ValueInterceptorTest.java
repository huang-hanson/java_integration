package com.hanson.kafka.test.producer;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * 自定义拦截器
 * 1.实现ProducerInterceptor接口
 * 2.定义泛型
 * 3.重写方法
 *
 * @author hanson
 * @date 2024/6/28 1:13
 */
public class ValueInterceptorTest implements ProducerInterceptor<String, String> {

    @Override
    // 发送数据时会调用此方法
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {

        return new ProducerRecord<String, String>(record.topic(), record.key(), record.value() + record.value());
    }

    @Override
    // 发送数据完毕，服务器返回响应，会调用此方法
    public void onAcknowledgement(RecordMetadata recordMetadata, Exception e) {

    }

    @Override
    // 生产者对象关闭的时候，会调用此方法
    public void close() {

    }

    @Override
    // 创建生产者的时候调用
    public void configure(Map<String, ?> map) {

    }
}
