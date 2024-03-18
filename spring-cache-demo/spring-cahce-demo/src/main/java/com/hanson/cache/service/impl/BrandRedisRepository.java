package com.hanson.cache.service.impl;

import com.hanson.cache.entity.Brand;
import com.hanson.cache.service.IBrandRedisRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author hanson
 * @date 2024/3/18 16:15
 */
@Slf4j
@Repository
public class BrandRedisRepository implements IBrandRedisRepository {

    @Autowired
    RedisTemplate<String, Brand> redisTemplate;

    public BrandRedisRepository() {
        log.debug("创建处理缓存的数据访问对象");
    }


    @Override
    public void save(Brand brand) {
        redisTemplate.opsForValue().set(getKey(brand.getId()), brand);
//        log.info("向redis中写入数据：{}", brand);
    }

    @Override
    public void save(List<Brand> brands) {
        ListOperations<String, Brand> ops = redisTemplate.opsForList();
        for (Brand brand : brands) {
            ops.rightPush(BRAND_LIST_KEY, brand);
        }

    }

    @Override
    public Brand getById(Integer id) {
        Brand brand = redisTemplate.opsForValue().get(getKey(id));
//        log.info("从redis中读取数据：{}", brand);
        return brand;
    }

    @Override
    public List<Brand> list() {
        int start = 0, end = -1;
        return list(start, end);
    }

    @Override
    public List<Brand> list(Integer start, Integer end) {
        ListOperations<String, Brand> ops = redisTemplate.opsForList();
        List<Brand> range = ops.range(BRAND_LIST_KEY, start, end);
        return range;
    }

    @Override
    public Long deleteAll() {
        Set<String> keys = redisTemplate.keys("brand:*");
        return redisTemplate.delete(keys);
    }

    private String getKey(Integer id) {
        return BRAND_ITEM_KEY_PREFIX + id;
    }
}
