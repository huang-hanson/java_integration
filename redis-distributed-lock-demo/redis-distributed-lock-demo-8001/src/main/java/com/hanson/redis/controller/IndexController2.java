package com.hanson.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@RestController
public class IndexController2 {

    // 使用ReentrantLock锁解决单体应用的并发问题
    Lock lock = new ReentrantLock();

    @Autowired
    StringRedisTemplate template;

    @RequestMapping("/buy2")
    public String index() {

        lock.lock();
        try {
            String result = template.opsForValue().get("goods:001");
            int total = result == null ? 0 : Integer.parseInt(result);
            if (total > 0) {
                int realTotal = total - 1;
                template.opsForValue().set("goods:001", String.valueOf(realTotal));
                System.out.println("购买商品成功，库存还剩：" + realTotal + "件， 服务端口为8001");
                return "购买商品成功，库存还剩：" + realTotal + "件， 服务端口为8001";
            } else {
                System.out.println("购买商品失败，服务端口为8001");
            }
        } catch (Exception e) {
            lock.unlock();
        } finally {
            lock.unlock();
        }
        return "购买商品失败，服务端口为8001";
    }
}
