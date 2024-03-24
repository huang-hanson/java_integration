package com.hanson.mockito.service;

import com.hanson.mockito.entity.User;

/**
 * @author hanson
 * @date 2024/3/22 15:29
 */
public interface RegistrationService {
    User register(String name, String phone) throws Exception;
}
