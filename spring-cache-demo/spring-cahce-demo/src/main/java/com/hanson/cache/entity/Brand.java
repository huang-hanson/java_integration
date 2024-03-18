package com.hanson.cache.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author hanson
 * @date 2024/3/18 15:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("brand")
public class Brand implements Serializable {
    private Integer id;
    private String name;
    private String description;
    private String keywords;
    // 其他属性...
}