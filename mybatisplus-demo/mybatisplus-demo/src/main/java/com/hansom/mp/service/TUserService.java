package com.hansom.mp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hansom.mp.entity.TUser;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author anonymous
 * @since 2024-07-17
 */
public interface TUserService extends IService<TUser> {

    List<TUser> getUserList();
}
