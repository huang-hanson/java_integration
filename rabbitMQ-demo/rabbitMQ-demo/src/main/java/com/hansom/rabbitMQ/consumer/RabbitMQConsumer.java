package com.hansom.rabbitMQ.consumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hansom.rabbitMQ.config.BaseRabbitMQConsumer;
import com.hansom.rabbitMQ.config.RabbitMQConfig;
import com.hansom.rabbitMQ.constant.MqConstant;
import com.hansom.rabbitMQ.constant.Result;
import com.hansom.rabbitMQ.entity.MqFailMessageQuery;
import com.hansom.rabbitMQ.enums.MQEnum;
import com.hansom.rabbitMQ.service.impl.StudentServiceImpl;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName RabbitMQConsumer
 * @Description TODO
 * @date 2024/7/22 13:57
 **/
@Slf4j
@Component
public class RabbitMQConsumer extends BaseRabbitMQConsumer {

    @Resource
    private StudentServiceImpl studentService;

    @Value("${wy.rabbitmq.mkt.recall.hwrta.retry.times:2}")
    public int WY_MKT_RECALL_HWRTA_CONSUMER_RETRY_TIMES;

    @RabbitListener(queues = RabbitMQConfig.TEST_QUEUE, containerFactory = "wyRabbitListener")
    public void mktRecallHwRtaQueue(Message message, Channel channel) throws IOException {
        String msgKey = "";
        try {
            JSONObject result = JSON.parseObject(new String(message.getBody()));
            String msgBody = result.getString("messageBody");
            msgKey = result.getString("messageKey");
            JSONObject jsonMsgBody = JSON.parseObject(msgBody);

            // TODO 异步的话，调用Open Feign
//            boolean ackStatus = checkConsumeResult(mktMediaClient.syncMktRecallHwRtaData(jsonMsgBody));

            // 这里暂时选择在本服务内写
            boolean ackStatus = checkConsumeResult(studentService.insertStudentData(jsonMsgBody));

            // 模拟消息发送失败
//            ackStatus = false;

            if (ackStatus) {
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
                return;
            }
        } catch (Exception e) {
            log.error("consumer error,queue name:{},data:{},error msg:{}", RabbitMQConfig.TEST_QUEUE, new String(message.getBody()), e.getMessage());
        }
        int num = getNumber(RabbitMQConfig.TEST_QUEUE, msgKey);
        if (num >= WY_MKT_RECALL_HWRTA_CONSUMER_RETRY_TIMES) {
            saveFailMessage(message, RabbitMQConfig.TEST_QUEUE);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);//确认
            log.info("consumer fail,queue name:{},data:{}", RabbitMQConfig.TEST_QUEUE, new String(message.getBody()));
            return;
        } else {
            addNumber(RabbitMQConfig.TEST_QUEUE, msgKey);
        }
        log.error("consumer error, record basicReject, queue name:{},data:{}", RabbitMQConfig.TEST_QUEUE, new String(message.getBody()));
        channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
    }

    private boolean checkConsumeResult(Result<Boolean> result) {
        if (result == null) {
            return false;
        }
        String status = result.getStatus();
        if (!MqConstant.REQUEST_SUCCESS_STATUS.equals(status)) {
            return false;
        }
        return result.getResultbody();
    }

    private void saveFailMessage(Message message, String queueName) {
        MqFailMessageQuery mqFailMessageQuery = new MqFailMessageQuery();
        mqFailMessageQuery.setMqName("im");
        mqFailMessageQuery.setQueueName(queueName);
        mqFailMessageQuery.setMessage(new String(message.getBody()));
        insertFailMessage(mqFailMessageQuery);
    }
}