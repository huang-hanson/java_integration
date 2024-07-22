package com.hansom.rabbitMQ.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hansom.rabbitMQ.entity.MqFailMessage;
import com.hansom.rabbitMQ.entity.MqFailMessageQuery;
import com.hansom.rabbitMQ.mapper.MqFailMessageMapper;
import com.hansom.rabbitMQ.entity.MessageActiveTaskReportDTO;
import com.hansom.rabbitMQ.service.MqFailMessageService;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName MqFailMessageServiceImpl
 * @Description TODO
 * @date 2024/7/22 14:12
 **/
@Service
public class MqFailMessageServiceImpl extends ServiceImpl<MqFailMessageMapper, MqFailMessage>
        implements MqFailMessageService {



    @Override
//    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED, value = "commonMysqlTransactionManager")
    public Boolean insertMqFailMessage(MqFailMessageQuery mqFailMessageQuery) {
        MqFailMessage mqFailMessage = new MqFailMessage();
        mqFailMessage.setMqName(mqFailMessageQuery.getMqName());
        mqFailMessage.setQueueName(mqFailMessageQuery.getQueueName());
        mqFailMessage.setMessage(mqFailMessageQuery.getMessage());
        mqFailMessage.setCreateDate(new Date());
        mqFailMessage.setUpdateDate(new Date());
        save(mqFailMessage);
        return true;
    }

    @Override
//    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED, value = "commonMysqlTransactionManager")
    public Boolean deleteById(Long id) {

        removeById(id);
        return true;
    }

    @Override
    public MessageActiveTaskReportDTO showTaskReport(Long dataGroupId, @Nullable LocalDate date) {
        return null;
    }
}
