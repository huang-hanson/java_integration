package com.hanson.cache.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hanson.cache.entity.Brand;
import com.hanson.cache.mapper.BrandMapper;
import com.hanson.cache.service.BrandService;
import com.hanson.cache.service.IBrandRedisRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author hanson
 * @date 2024/3/18 15:04
 */
@Slf4j
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandService {

//    @Autowired
//    IBrandRedisRepository redisRepository;
//
    @Autowired
    BrandMapper brandMapper;
//
//    @Override
//    public Brand getById(Integer id) {
//        Brand brand = null;
//        brand = redisRepository.getById(id);
//        if (brand != null) {
//            log.info("从缓存中拿到数据：{}", brand);
//            return brand;
//        }
//
//        //缓存中没有这个品牌信息，所以从数据库中拿
//        brand = brandMapper.selectById(id);
//        if (brand != null){
//            //将品牌信息存入缓存，这里可以使用异步的方式进行
//            redisRepository.save(brand);
//            log.info("从数据库拿到品牌数据：{}",brand);
//            return brand;
//        }
//        return null;
//    }

//    @Override
//    @Cacheable(value = "brands",key = "#id",condition = "#id%2==0")
//    public Brand getById(Integer id) {
//        Brand brand = brandMapper.selectById(id);
//        return brand;
//    }

    @Override
    @Cacheable(value = "brands",key = "#id",condition = "#id%2==0",cacheManager = "cacheManager")
    public Brand getById(Integer id) {
        Brand brand = brandMapper.selectById(id);
        return brand;
    }
}
