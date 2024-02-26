package com.hanson.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
public class IndexController6 {

    private static final String REDIS_LOCK = "lock";

    @Autowired
    StringRedisTemplate template;

    @RequestMapping("/buy6")
    public String index() {

        // 每个人进来先要枷锁，key的值为”lock“
        String value = UUID.randomUUID().toString().replace("-", "");

        try {
            // 为key增加一个过期时间
            Boolean flag = template.opsForValue().setIfAbsent(REDIS_LOCK, value, 10L, TimeUnit.SECONDS);

            // 加锁失败
            if (!flag){
                return "枪锁失败！";
            }

            System.out.println(value + "枪锁成功！");

            String result = template.opsForValue().get("goods:001");

            int total = result == null ? 0 : Integer.parseInt(result);
            if (total > 0){
                // 如果在此处需要调用其他微服务，处理时间较长。。。
                int realTotal = total - 1;
                template.opsForValue().set("goods:001",String.valueOf(realTotal));
                System.out.println("购买商品成功，库存还剩：" + realTotal + "件， 服务端口为8002");
                return "购买商品成功，库存还剩：" + realTotal + "件， 服务端口为8002";
            }else {
                System.out.println("购买商品失败，服务端口为8002");

            }
            return "购买商品失败，服务端口为8002";
        }finally {
            // 谁加的锁，谁才能删锁！！！！
            String lockValue = template.opsForValue().get(REDIS_LOCK);
            if (lockValue != null && lockValue.equals(value)){
                template.delete(REDIS_LOCK);
            }
        }

    }
}
