package com.hanson.test.service;

import com.hanson.test.entity.User;

/**
 * @author hanson
 * @date 2024/3/22 1:07
 */
public interface UserService {

    /**
     * 根据name查找user
     * @param name
     * @return
     */
    User getUserByName(String name);

    /**
     * 保存user
     * @param user
     * @return
     */
    Integer saveUser(User user);
}
