package com.hanson.cache.config;

import net.spy.memcached.MemcachedClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * @author hanson
 * @date 2024/3/21 15:37
 */
@Configuration
public class SpyMemcacheConfig implements CommandLineRunner {

    @Value("${memcache.ip}")
    private String ip;

    @Value("${memcache.port}")
    private int port;

    private MemcachedClient client = null;

    @Override
    public void run(String... args) throws Exception {
        try {
            client = new MemcachedClient(new InetSocketAddress(ip, port));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public MemcachedClient getClient(){
        return client;
    }
}
