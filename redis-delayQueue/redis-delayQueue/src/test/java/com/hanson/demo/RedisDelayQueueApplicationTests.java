package com.hanson.demo;

//import com.hanson.demo.consumer.TaskConsumer;
//import com.hanson.demo.producer.TaskProducer;
//import org.redisson.Redisson;
//import org.redisson.api.RBlockingQueue;
//import org.redisson.api.RDelayedQueue;
//import org.redisson.api.RedissonClient;
//import org.redisson.config.Config;
import com.hanson.demo.utils.DelayQueue;
import org.junit.jupiter.api.Test;
import com.hanson.demo.entity.DelayMessage;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class RedisDelayQueueApplicationTests {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private DelayQueue delayQueue;

//    @Resource
//    private RedissonClient redisson;

    // 通过监听过期时间
    @Test
    void redisDelayQueueMethod1() {
        stringRedisTemplate.opsForValue().set("key_01", "value_01", 5, TimeUnit.SECONDS);
        stringRedisTemplate.opsForValue().set("key_02", "value_02", 20, TimeUnit.SECONDS);
        stringRedisTemplate.opsForValue().set("key_03", "value_03", 15, TimeUnit.SECONDS);
        stringRedisTemplate.opsForValue().set("key_04", "value_04", 10, TimeUnit.SECONDS);
    }

    // 通过zset实现延时队列
    @Test
    void redisDelayQueueMethod2() {
        // 获取当前时间戳
        long currentTime = System.currentTimeMillis();
        System.out.println("当前时间戳：" + currentTime);
        delayQueue.put(new DelayMessage("1", "westbrook", currentTime + 10000));
        delayQueue.put(new DelayMessage("0", "love", currentTime + 10000));
        delayQueue.put(new DelayMessage("6", "brook", currentTime + 10000));
        delayQueue.put(new DelayMessage("7", "wade", currentTime + 10000));
        delayQueue.put(new DelayMessage("8", "lebron", currentTime + 10000));
        delayQueue.put(new DelayMessage("2", "james", currentTime + 20000));
        delayQueue.put(new DelayMessage("3", "kobe", currentTime + 30000));
        delayQueue.put(new DelayMessage("4", "curry", currentTime + 40000));
        delayQueue.put(new DelayMessage("9", "durant", currentTime + 50000));
        delayQueue.put(new DelayMessage("10", "paul", currentTime + 50000));
        delayQueue.put(new DelayMessage("5", "durant", currentTime + 50000));
    }

    // 方法三：通过Redisson实现延时队列
//    @Test
//    void redisDelayQueueMethod3() {
//
//        RBlockingQueue<String> blockingQueue = redisson.getBlockingQueue("delayQueue");
//        RDelayedQueue<String> delayedQueue = redisson.getDelayedQueue(blockingQueue);
//
//        Thread producerThread = new Thread(new TaskProducer(delayedQueue));
//        Thread consumerThread = new Thread(new TaskConsumer(blockingQueue));
//
//        producerThread.start();
//        consumerThread.start();
//    }
}
