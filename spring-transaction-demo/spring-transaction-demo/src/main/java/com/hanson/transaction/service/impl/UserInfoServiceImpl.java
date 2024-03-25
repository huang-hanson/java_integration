package com.hanson.transaction.service.impl;

/**
 * @author hanson
 * @date 2024/3/26 0:27
 */
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hanson.transaction.domain.UserInfo;
import com.hanson.transaction.mapper.UserInfoMapper;
import com.hanson.transaction.service.UserInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;


    @Override
    @Transactional(propagation = Propagation.NESTED)
    public int add(UserInfo userInfo) {
        int result = userInfoMapper.insert(userInfo);
        return result;
    }
}

