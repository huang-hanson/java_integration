package com.hanson.redis.controller;


import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class IndexController8 {

    public static final String REDIS_LOCK = "lock";

    @Autowired
    StringRedisTemplate template;

    private final RedissonClient redissonClient;

    @Autowired
    public IndexController8(RedissonClient redissonClient){
        this.redissonClient = redissonClient;
    }

    @RequestMapping("/buy8")
    public String index(){

        RLock lock = redissonClient.getLock(REDIS_LOCK);
        lock.lock();

        // 每个人进来先要进行加锁，key值为"lock"
        String value = UUID.randomUUID().toString().replace("-","");
        try {
            String result = template.opsForValue().get("goods:001");
            int total = result == null ? 0 : Integer.parseInt(result);
            if (total > 0) {
                // 如果在此处需要调用其他微服务，处理时间较长。。。
                int realTotal = total - 1;
                template.opsForValue().set("goods:001", String.valueOf(realTotal));
                System.out.println("购买商品成功，库存还剩：" + realTotal + "件， 服务端口为8001");
                return "购买商品成功，库存还剩：" + realTotal + "件， 服务端口为8001";
            } else {
                System.out.println("购买商品失败，服务端口为8001");
            }
            return "购买商品失败，服务端口为8001";

        }finally {
            if (lock.isLocked() && lock.isHeldByCurrentThread()){
                lock.unlock();
            }
        }
    }
}
