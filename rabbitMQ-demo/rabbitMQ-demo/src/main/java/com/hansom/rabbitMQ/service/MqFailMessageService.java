package com.hansom.rabbitMQ.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hansom.rabbitMQ.entity.MessageActiveTaskReportDTO;
import com.hansom.rabbitMQ.entity.MqFailMessage;
import com.hansom.rabbitMQ.entity.MqFailMessageQuery;

import java.time.LocalDate;

/**
 * @author hanson.huang
 * @version V1.0
 * @InterfaceName MqFailMessageService
 * @Description TODO
 * @date 2024/7/22 14:08
 **/
public interface MqFailMessageService extends IService<MqFailMessage> {
    /**
     * 新建消息
     * @param mqFailMessageQuery MqFailMessageQuery
     * @return Boolean
     */
    Boolean insertMqFailMessage(MqFailMessageQuery mqFailMessageQuery);

    /**
     * 删除消息
     * @param id 主键id
     * @return Boolean
     */
    Boolean deleteById(Long id);

    /**
     * 获取消息
     * @param dataGroupId 由数据组维护的任务id
     * @param date 报表日期
     * @return MessageActiveTaskReportDTO
     */
    MessageActiveTaskReportDTO showTaskReport(Long dataGroupId, LocalDate date);
}
