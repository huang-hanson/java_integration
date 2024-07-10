package com.hanson.datapreheat.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanson.datapreheat.entity.User;
import com.hanson.datapreheat.service.UserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author hanson
 * @date 2024/7/11 0:59
 */
@Component
public class RedisHandler implements InitializingBean {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserService userService;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void afterPropertiesSet() throws Exception {

        // 初始化缓存
        // 1.查询所有用户信息
        List<User> userList = userService.list();

        // 2.放入缓存中
        for (User user : userList) {

            // 2.1 对user对象序列化为JSON
            String json = objectMapper.writeValueAsString(user);

            // 2.2 设置key前缀，并将过期时间设置为24小时
            stringRedisTemplate.opsForValue().set("user:" + user.getId(), json, 1, TimeUnit.DAYS);
        }

    }
}
