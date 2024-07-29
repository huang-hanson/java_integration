package com.hansom.rabbitMQ.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName MqFailMessage
 * @Description TODO
 * @date 2024/7/22 14:08
 **/
@TableName(value ="mq_fail_message", autoResultMap = true)
@Data
public class MqFailMessage {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 集群名字
     */
    @TableField("mq_name")
    private String mqName;

    /**
     * 队列名
     */
    @TableField("queue_name")
    private String queueName;

    /**
     * 消息体
     */
    @TableField("message")
    private String message;

    /**
     * 创建时间
     */
    @TableField("create_date")
    private Date createDate;

    /**
     * 更新时间
     */
    @TableField("update_date")
    private Date updateDate;
}
