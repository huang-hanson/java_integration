package com.hanson.demo.config;

import io.lettuce.core.ReadFrom;
import io.lettuce.core.TimeoutOptions;
import io.lettuce.core.cluster.ClusterClientOptions;
import io.lettuce.core.cluster.ClusterTopologyRefreshOptions;
import lombok.Getter;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author linton.cao
 */
@Configuration
public class BaseRedisLettuceConfig {
    @Resource
    public Environment environment;

    @Configuration
    @Getter
    public class RedisPoolCustomerConfig {
        @Value("${spring.redis.lettuce.pool.max-active:40}")
        protected int maxActive;

        @Value("${spring.redis.lettuce.pool.max-idle:20}")
        protected int maxIdle;

        @Value("${spring.redis.lettuce.pool.min-idle:3}")
        protected int minIdle;

        @Value("${spring.redis.lettuce.pool.max-wait:-1}")
        protected int maxWait;
    }

    /**
     * 连接池配置
     * @return org.apache.commons.pool2.impl.GenericObjectPoolConfig
     * @author linton.cao
     * @date 2022/1/20 13:49
     */
    @Bean("redisPoolConfig")
    @ConditionalOnMissingBean(name = { "redisPoolConfig" })
    @Primary
    @ConfigurationProperties(prefix = "spring.redis.lettuce.pool")
    public GenericObjectPoolConfig<?> redisPoolConfig(@Autowired RedisPoolCustomerConfig redisPoolCustomerConfig) {
        GenericObjectPoolConfig<?> genericObjectPoolConfig = new GenericObjectPoolConfig<>();
        genericObjectPoolConfig.setMaxTotal(redisPoolCustomerConfig.getMaxActive());
        genericObjectPoolConfig.setMaxIdle(redisPoolCustomerConfig.getMaxIdle());
        genericObjectPoolConfig.setMinIdle(redisPoolCustomerConfig.getMinIdle());
        genericObjectPoolConfig.setMaxWaitMillis(redisPoolCustomerConfig.getMaxWait());
        return genericObjectPoolConfig;
    }

    /**
     * 客户端配置
     * @return org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration
     * @author linton.cao
     * @date 2022/1/20 13:33
     */
    @Bean("redisClientConfig")
    @ConditionalOnMissingBean(name = { "redisClientConfig" })
    @Primary
    public LettuceClientConfiguration redisClientConfig(@Autowired @Qualifier("redisPoolConfig") GenericObjectPoolConfig<?> redisPoolConfig){
        ClusterTopologyRefreshOptions topologyRefreshOptions = ClusterTopologyRefreshOptions.builder()
                //按照周期刷新拓扑
                .enablePeriodicRefresh(Duration.ofSeconds(10))
                //根据事件刷新拓扑
                .enableAllAdaptiveRefreshTriggers()
                .build();

        ClusterClientOptions clusterClientOptions = ClusterClientOptions.builder()
                //redis命令超时时间,超时后才会使用新的拓扑信息重新建立连接
                .timeoutOptions(TimeoutOptions.enabled(Duration.ofSeconds(10)))
                .topologyRefreshOptions(topologyRefreshOptions)
                .build();

        return LettucePoolingClientConfiguration.builder()
                .poolConfig(redisPoolConfig)
                .readFrom(ReadFrom.ANY)
                .clientOptions(clusterClientOptions)
                .build();
    }

    /**
     * 服务端配置
     * @return org.springframework.data.redis.connection.RedisClusterConfiguration
     * @author linton.cao
     * @date 2022/1/20 13:33
     */
    @Bean("redisClusterConfig")
    @ConditionalOnMissingBean(name = "redisClusterConfig")
    @Primary
    public RedisClusterConfiguration redisClusterConfig() {
        Map<String, Object> source = new HashMap<>(8);
        source.put("spring.redis.cluster.nodes", environment.getProperty("spring.redis.cluster.nodes"));
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration(new MapPropertySource("RedisClusterConfiguration", source));
        redisClusterConfiguration.setPassword(environment.getProperty("spring.redis.password"));
        return redisClusterConfiguration;
    }

    /**
     * 连接工厂配置
     * @return org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
     * @author linton.cao
     * @date 2022/1/20 13:37
     */
    @Bean("lettuceConnectionFactory")
    @ConditionalOnMissingBean(name = "lettuceConnectionFactory")
    @Primary
    public LettuceConnectionFactory lettuceConnectionFactory(
            @Autowired @Qualifier("redisClientConfig") LettuceClientConfiguration redisClientConfig,
            @Autowired @Qualifier("redisClusterConfig") RedisClusterConfiguration redisClusterConfig
    ) {
        return new LettuceConnectionFactory(redisClusterConfig, redisClientConfig);
    }

}
