package com.hanson.datapreheat.controller;

import com.hanson.datapreheat.entity.User;
import com.hanson.datapreheat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author hanson
 * @date 2024/7/11 0:33
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 查询所有用户信息
     * @return
     */
    @GetMapping("list")
    public List<User> getUsers() {
        return userService.list();
    }

    /**
     * 根据ID查询用户信息
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getById(id);
    }
}
