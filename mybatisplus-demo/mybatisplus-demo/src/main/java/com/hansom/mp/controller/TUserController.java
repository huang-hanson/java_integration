package com.hansom.mp.controller;


import com.hansom.mp.entity.TUser;
import com.hansom.mp.service.TUserService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author anonymous
 * @since 2024-07-17
 */
@RestController
@RequestMapping("/t-user")
public class TUserController {

    @Resource
    private TUserService userService;

    // 查询用户列表
    @RequestMapping("/list")
    public List<TUser> list() {
        return userService.getUserList();
    }

}
