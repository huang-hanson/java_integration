package com.hanson.redis.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Product implements Serializable {

    // 商品id
    private Long id;

    // 商品名称
    private String productName;

    // ......其他属性

}
