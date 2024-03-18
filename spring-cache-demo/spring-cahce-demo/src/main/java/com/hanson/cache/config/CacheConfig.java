package com.hanson.cache.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.time.Duration;

/**
 * @author hanson
 * @date 2024/3/18 20:33
 */
@Configuration
public class CacheConfig {

    @Bean("cacheManager")
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory){
        //通过下面的instanceConfig()方法获得一个RedisCacheConfiguration;
        RedisCacheConfiguration configuration = instanceConfig();

        return RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(configuration)//设置默认参数
                .transactionAware()//事务感知
                .build();
    }
    private RedisCacheConfiguration instanceConfig(){
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(60L))//设置过期时间为60秒
                .disableCachingNullValues();//不缓存空对象
    }
}
