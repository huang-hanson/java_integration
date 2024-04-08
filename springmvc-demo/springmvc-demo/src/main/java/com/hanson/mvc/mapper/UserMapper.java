package com.hanson.mvc.mapper;

import com.hanson.mvc.domain.Userinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author hanson
 * @date 2024/4/9 2:27
 */
@Mapper
public interface UserMapper {

    /**
     * 登录逻辑
     * @param username
     * @param password
     * @return
     */
    Userinfo login(@Param("username") String username,
                   @Param("password") String password);
}
