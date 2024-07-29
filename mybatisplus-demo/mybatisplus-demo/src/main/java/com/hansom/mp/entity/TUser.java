package com.hansom.mp.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.Delegate;

/**
 * <p>
 * 
 * </p>
 *
 * @author Hanson
 * @since 2024-07-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_user")
public class TUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "username")
    private String username;

    @TableField(value = "password")
    private String password;

    @TableField(value = "birthday")
    private LocalDate birthday;

    /**
     * 0是女生，1是男生
     */
    @TableField(value = "gender")
    private Integer gender;

    @TableField(value = "create_time")
    private LocalDateTime createTime;

    @TableField(value = "update_time")
    private LocalDateTime updateTime;

    /**
     * 0删除，1未删除
     */
    @TableField(value = "is_del")
    @TableLogic
    private Integer isDel;


}
