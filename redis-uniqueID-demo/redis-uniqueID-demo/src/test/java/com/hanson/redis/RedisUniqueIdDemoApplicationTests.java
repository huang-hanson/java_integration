package com.hanson.redis;

import com.hanson.redis.utils.RedisGenerateIDUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisUniqueIdDemoApplication.class)
class RedisUniqueIdDemoApplicationTests {

    @Resource
    private RedisGenerateIDUtils redisGenerateIDUtils;


    @Test
    public void test() throws InterruptedException {
        // 定义一个线程池 设置核心线程数和最大线程数都为100，队列根据需要设置
        ThreadPoolExecutor executor = new ThreadPoolExecutor(100, 100, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>(10000));
        CountDownLatch countDownLatch = new CountDownLatch(10000);

        long beginTime = System.currentTimeMillis();
        // 获取10000个全局唯一ID 看看是否有重复
        CopyOnWriteArraySet<String> ids = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 10000; i++) {
            executor.execute(() -> {
                // 获取全局唯一ID
                long beginTime02 = System.currentTimeMillis();
                String orderNo = redisGenerateIDUtils.generateId("NO");
                System.out.println(orderNo);
                System.out.println("获取单个ID耗时 time=" + (System.currentTimeMillis() - beginTime02));
                if (ids.contains(orderNo)) {
                    System.out.println("重复ID=" + orderNo);
                } else {
                    ids.add(orderNo);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        // 打印获取到的全局唯一ID集合数量
        System.out.println("获取到全局唯一ID count=" + ids.size());
        System.out.println("耗时毫秒 time=" + (System.currentTimeMillis() - beginTime));

    }

}
