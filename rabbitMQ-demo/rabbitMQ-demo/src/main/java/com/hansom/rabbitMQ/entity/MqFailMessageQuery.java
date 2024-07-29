package com.hansom.rabbitMQ.entity;

import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName MqFailMessageQuery
 * @Description TODO
 * @date 2024/7/22 14:11
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MqFailMessageQuery {
    /**
     * 集群名字
     */
    @NotNull
    private String mqName;

    /**
     * 队列名
     */
    @NotNull
    private String queueName;

    /**
     * 消息体
     */
    @NotNull
    private String message;
}