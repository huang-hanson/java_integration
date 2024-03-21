package com.hanson.test.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hanson
 * @date 2024/3/22 1:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    /**
     * 姓名，登录密码
     */
    private String name;
    private String password;
}
