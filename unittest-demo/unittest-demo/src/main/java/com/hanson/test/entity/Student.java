package com.hanson.test.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author hanson
 * @date 2024/3/21 18:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("student")
public class Student implements Serializable {
    private Integer id;
    private String name;
    private String classname;
    private Integer age;
    private String sex;
}
