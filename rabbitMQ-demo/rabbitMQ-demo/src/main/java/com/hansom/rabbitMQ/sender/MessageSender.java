package com.hansom.rabbitMQ.sender;

import com.alibaba.fastjson.JSON;
import com.hansom.rabbitMQ.entity.StudentInfo;
import com.hansom.rabbitMQ.enums.MQEnum;
import com.hansom.rabbitMQ.util.RabbitMQMessageUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Date;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName MessageSender
 * @Description TODO
 * @date 2024/7/22 12:16
 **/
@Component
public class MessageSender {

    @Value("${mkt.recall.hwrta.data.rabbitmq.producer.retry-times:3}")
    private int retryTimes;

    @Resource(name = "wyRabbitTemplate")
    private RabbitTemplate wyRabbitTemplate;

    @Scheduled(fixedRate = 10000) // 每10秒执行一次
    public void sendStudentDataToMQ() {
        StudentInfo student = new StudentInfo();
        student.setName("John Doe");
        student.setNumber(12345);
        student.setCreateTime(new Date());
        student.setUpdateTime(new Date());

        try {
            RabbitMQMessageUtils.MQProducter(wyRabbitTemplate, MQEnum.TEST_MQ, JSON.toJSONString(student), retryTimes);
        } catch (Exception e) {
            // 这里的日志记录是为了捕获异常和调试
            e.printStackTrace();
            // 根据需要可以添加更多的日志记录或错误处理
        }
    }
}