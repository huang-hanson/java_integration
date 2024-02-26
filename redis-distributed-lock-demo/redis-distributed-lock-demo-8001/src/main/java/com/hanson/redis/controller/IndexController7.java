package com.hanson.redis.controller;


import com.hanson.redis.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
public class IndexController7 {

    private static final String REDIS_LOCK = "lock";

    @Autowired
    StringRedisTemplate template;

    @RequestMapping("/buy7")
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
                System.out.println("购买商品成功，库存还剩：" + realTotal + "件， 服务端口为8001");
                return "购买商品成功，库存还剩：" + realTotal + "件， 服务端口为8001";
            }else {
                System.out.println("购买商品失败，服务端口为8001");

            }
            return "购买商品失败，服务端口为8001";
        }finally {

            // 谁加的锁，谁才能删除，使用Lua脚本，进行锁的删除

            Jedis jedis = null;
            try {
                jedis = RedisUtils.getJedis();

                String script = "if redis.call('get',KEYS[1]) == ARGV[1] " +
                        "then " +
                        "return redis.call('del',KEYS[1]) " +
                        "else " +
                        "   return 0 " +
                        "end";

                Object eval = jedis.eval(script, Collections.singletonList(REDIS_LOCK), Collections.singletonList(value));
                if("1".equals(eval.toString())){
                    System.out.println("-----del redis lock ok....");
                }else{
//                    System.out.println("-----del redis lock error ....");
                }
            }catch (Exception e){

            }finally {
                if(null != jedis){
                    jedis.close();
                }
            }

        }

    }
}
