package com.hanson.ioc.bean;

import com.hanson.ioc.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @author hanson
 * @date 2024/4/8 21:09
 */
@Component
public class BeanConfig {

    @Primary // 指定该bean为默认的bean实现.
    @Bean("u1")
    public User user1(){
        User user = new User();
        user.setName("Hanson1");
        user.setAge(20);
        return user;
    }

    @Bean()
    public User user2(){
        User user = new User();
        user.setName("Hanson2");
        user.setAge(20);
        return user;
    }
}
