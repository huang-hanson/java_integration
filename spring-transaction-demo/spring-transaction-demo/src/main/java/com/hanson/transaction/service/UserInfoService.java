package com.hanson.transaction.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hanson.transaction.domain.UserInfo;

/**
 * @author hanson
 * @date 2024/3/26 0:22
 */
public interface UserInfoService extends IService<UserInfo> {

    int add(UserInfo userInfo);
}

