package com.hanson.cache.util;

import com.hanson.cache.entity.Brand;
import com.hanson.cache.mapper.BrandMapper;
import com.hanson.cache.service.BrandService;
import com.hanson.cache.service.IBrandRedisRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author hanson
 * @date 2024/3/18 16:48
 */
//@Slf4j
//@Component
//public class CachePreLoad implements ApplicationRunner {
//
//    @Autowired
//    BrandService brandService;
//
//    @Autowired
//    IBrandRedisRepository redisRepository;
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        log.debug("开始缓存预热...");
//        log.debug("从MySQL中读取品牌列表");
//        List<Brand> brands = brandService.list();
//        log.debug("删除Redis原有的数据");
//        redisRepository.deleteAll();
//        log.debug("将品牌列表写入到Redis");
//        redisRepository.save(brands);
//        for (Brand brand : brands) {
//            redisRepository.save(brand);
//        }
//        log.debug("缓存预热完成");
//    }
//}
