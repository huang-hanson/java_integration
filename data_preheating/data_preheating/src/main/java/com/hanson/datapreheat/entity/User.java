package com.hanson.datapreheat.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author hanson
 * @date 2024/7/11 0:35
 */
@Data
@TableName("db_user")
public class User {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("name")
    private String name;

    @TableField("gender")
    private String gender;

    @TableField("image")
    private String image;

    @TableField("job")
    private String job;

    @TableField("entrydate")
    private String entrydate;

    @TableField("dept_id")
    private Integer deptId;

    @TableField("create_time")
    private String createTime;

    @TableField("update_time")
    private String updateTime;

    @TableField("is_del")
//    @TableLogic
    private String isDel;
}
