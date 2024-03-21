package com.hanson.test.service.impl;

import com.hanson.test.dao.UserDao;
import com.hanson.test.entity.User;
import com.hanson.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author hanson
 * @date 2024/3/22 1:08
 */
@Service
public class UserServiceImpl implements UserService {

    //userDao
    @Resource
    private UserDao userDao;

    /**
     * 根据name查找user
     * @param name
     * @return
     */
    @Override
    public User getUserByName(String name) {
        try {
            return userDao.getUserByName(name);
        } catch (Exception e) {
            throw new RuntimeException("查询user异常");
        }
    }

    /**
     * 保存user
     * @param user
     * @return
     */
    @Override
    public Integer saveUser(User user) {
        if (userDao.getUserByName(user.getName()) != null) {
            throw new RuntimeException("用户名已存在");
        }
        try {
            return userDao.saveUser(user);
        } catch (Exception e) {
            throw new RuntimeException("保存用户异常");
        }
    }
}
