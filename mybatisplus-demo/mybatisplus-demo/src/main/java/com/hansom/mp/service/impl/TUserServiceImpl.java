package com.hansom.mp.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hansom.mp.entity.TUser;
import com.hansom.mp.mapper.TUserMapper;
import com.hansom.mp.service.TUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author anonymous
 * @since 2024-07-17
 */
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements TUserService {

    @Resource
    private TUserMapper userMapper;

    @Override
    public List<TUser> getUserList() {
        return userMapper.selectList(new QueryWrapper<>());
    }
}
