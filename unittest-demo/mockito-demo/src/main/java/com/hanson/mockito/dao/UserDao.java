package com.hanson.mockito.dao;

import com.hanson.mockito.entity.User;

import java.sql.SQLException;

/**
 * @author hanson
 * @date 2024/3/22 15:27
 */
public class UserDao {

    public User save(String name, String phone, String repId) throws SQLException {
        return new User(name, phone, repId);
    }
}
