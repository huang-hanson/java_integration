package com.hanson.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName DelayMessage
 * @Description TODO
 * @date 2024/7/11 16:52
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DelayMessage implements Serializable {

    /**
     * 切记实例化
     */
    private static final long serialVersionUID = -7671756385477179547L;

    /**
     * 消息 id
     */
    private String id;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 消息到期时间(指定当前消息在什么时间开始消费(时间戳))
     */
    private long expireTime;
}