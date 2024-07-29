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
 * @ClassName StudentInfo
 * @Description TODO
 * @date 2024/7/22 14:39
 **/
@Data
@TableName("t_student_info")
public class StudentInfo {

    @TableId(value = "id", type = IdType.AUTO) // 指定自增策略
    private Integer id;

    @TableField("name")
    private String name;

    @TableField("number")
    private Integer number;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;
}