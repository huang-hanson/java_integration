package com.hanson.cache;

import com.hanson.cache.config.SpyMemcacheConfig;
import com.whalin.MemCached.MemCachedClient;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.concurrent.TimeoutException;

@SpringBootTest
class CacheDemoApplicationTests {

    /**
     * Memcached-java-client方式
     */
    @Autowired
    private MemCachedClient memCachedClient;

    /**
     * 使用 SpyMemcached 方式
     */
    @Autowired
    private SpyMemcacheConfig spyMemcacheConfig;

    /**
     * 使用 XMemcached 方式
     */
    @Autowired
    private MemcachedClient memcachedClient;

    @Test
    void contextLoads() {
        System.out.println("测试memCached方法");
    }

    /**
     *  Memcached-java-client 客户端推出较早，应用最广泛，阻塞式 IO，稳定性高，高并发下性能低。较早推出的 Memcached JAVA 客户端 API，应用广泛，运行比较稳定，使用阻塞 IO，不支持 CAS 操作，已停止更新。
     */
    @Test
    void memCacheJavaClient() throws InterruptedException {
        // 放入缓存
        boolean flag = memCachedClient.set("a", 1);
        System.out.println(flag);
        // 取出缓存
        Object a = memCachedClient.get("a");
        System.out.println(a);
        // 3s后过期
        memCachedClient.set("b", "2", new Date(3000));
        Object b = memCachedClient.get("b");
        System.out.println(b);

        Thread.sleep(3000);
        b = memCachedClient.get("b");
        System.out.println(a);
        System.out.println(b);
    }


    /**
     *  Spymemcached 客户端时基于 JDK 的 Concurrent 和 NIO，存取速度高，并发性能高，支持 CAS 操作，已停止更新。
     */
    @Test
    void spyMemCached() throws InterruptedException {
        /*这个过期时间单位是秒，最大值是60*60*24*30*/
        spyMemcacheConfig.getClient().set("spyMemcachedKey",1,"Hanson");
        System.out.println("基于spyMemcached实现，现在的值为 "+spyMemcacheConfig.getClient().get("spyMemcachedKey"));
        Thread.sleep(2000);
        System.out.println("1秒后缓存内容清除，现在的值为： "+spyMemcacheConfig.getClient().get("spyMemcachedKey"));
    }

    /**
     * XMemcached 客户端同样基于 NIO 实现，减少了线程创建和切换的开销，这一点在高并发下特别明显，一直更新。推荐使用。
     */
    @Test
    void xMemCached(){
        try {
            // 新增操作
            memcachedClient.set("XMemcacheKeyOne",0,"Hanson");
            System.out.println((String) memcachedClient.get("XMemcacheKeyOne"));

            // 删除操作
            memcachedClient.delete("XMemcacheKeyOne");
            System.out.println((String) memcachedClient.get("XMemcacheKeyOne"));

            // 设置存活时间
            memcachedClient.set("XMemcacheKeyTwo",1,"李四");
            Thread.sleep(2000);
            System.out.println((String)memcachedClient.get("XMemcacheKeyTwo"));

            // 更新操作
            memcachedClient.set("XMemcacheKeyThree",0,"Huang");
            System.out.println((String)memcachedClient.get("XMemcacheKeyThree"));
            memcachedClient.set("XMemcacheKeyThree",0,"Hanson Huang");
            System.out.println((String)memcachedClient.get("XMemcacheKeyThree"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
