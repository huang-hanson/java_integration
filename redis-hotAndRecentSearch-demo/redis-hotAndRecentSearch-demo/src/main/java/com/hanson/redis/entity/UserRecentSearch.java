package com.hanson.redis.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRecentSearch implements Serializable {

    /**
     * 搜索信息
     */
    private String searchInfo;


    /**
     * 用户id
     */
    private Long unionId;
}
