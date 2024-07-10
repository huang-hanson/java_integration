package com.hanson.datapreheat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hanson.datapreheat.entity.User;
import com.hanson.datapreheat.mapper.UserMapper;
import com.hanson.datapreheat.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author hanson
 * @date 2024/7/11 0:44
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
