package com.hanson.lz4.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName BusinessObject
 * @Description TODO
 * @date 2024/7/23 9:43
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessObject {
    private String id;
    private String name;
    private int age;
    private String address;
    private String description;
    private Date createdDate;
}