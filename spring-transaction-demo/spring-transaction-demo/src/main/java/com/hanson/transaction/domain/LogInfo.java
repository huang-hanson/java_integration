package com.hanson.transaction.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author hanson
 * @date 2024/3/26 2:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("loginfo")
public class LogInfo {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String desc;
    private Date createTime;
}
