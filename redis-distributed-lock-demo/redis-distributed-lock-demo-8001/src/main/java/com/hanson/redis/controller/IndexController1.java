package com.hanson.redis.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController1 {

    @Autowired
    StringRedisTemplate template;

    @RequestMapping("/buy1")
    public String index() {
        // Redis中存有goods:001号商品，数量为100
        String result = template.opsForValue().get("goods:001");
        // 获取到剩余商品数
        int total = result == null ? 0 : Integer.parseInt(result);
        if (total > 0) {
            // 剩余商品数大于0 ，则进行扣减
            int realTotal = total - 1;
            // 将商品数回写数据库
            template.opsForValue().set("goods:001", String.valueOf(realTotal));
            System.out.println("购买商品成功，库存还剩：" + realTotal + "件， 服务端口为8001");
            return "购买商品成功，库存还剩：" + realTotal + "件， 服务端口为8001";
        } else {
            System.out.println("购买商品失败，服务端口为8001");
        }
        return "购买商品失败，服务端口为8001";
    }

}
