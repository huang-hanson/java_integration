package com.hanson.datapreheat.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanson.datapreheat.entity.User;
import com.hanson.datapreheat.service.UserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
//        List<User> userList = userService.list();

        // 没有数据库，可以造数据
        ArrayList<User> userList = new ArrayList<>();
        User user1 = new User();
        user1.setId(1);
        user1.setUsername("hanson");
        user1.setPassword("123456");
        user1.setGender("男");
        user1.setImage("h.jpg");
        user1.setJob("Java开发工程师");
        user1.setEntrydate("2020-01-01");
        user1.setName("黄忠");
        user1.setDeptId(1);
        user1.setCreateTime("2020-01-01 00:00:00");
        user1.setUpdateTime("2020-01-01 00:00:00");
        user1.setIsDel("0");
        userList.add(user1);

        // 2.放入缓存中
        for (User user : userList) {

            // 2.1 对user对象序列化为JSON
            String json = objectMapper.writeValueAsString(user);

            // 2.2 设置key前缀，并将过期时间设置为24小时
            stringRedisTemplate.opsForValue().set("user:" + user.getId(), json, 1, TimeUnit.DAYS);
        }

    }
}
