package com.hanson.transaction.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author hanson
 * @date 2024/3/26 0:25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@TableName("user")
public class UserInfo {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String username;

    private String password;

    private String photo;

    private Date createTime;

    private Date updateTime;

    /**
     * 用户状态 0 1
     * 0 离线
     * 1 在线
     */
    private Integer status;
}

