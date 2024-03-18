package com.hanson.cache.service;

import com.hanson.cache.entity.Brand;

import java.util.List;

/**
 * @author hanson
 * @date 2024/3/18 16:14
 */
public interface IBrandRedisRepository {
    String BRAND_ITEM_KEY_PREFIX = "brand:item:";
    String BRAND_LIST_KEY = "brand:list";
    void save(Brand brand);
    void save(List<Brand> brands);
    Brand getById(Integer id);
    List<Brand> list();
    List<Brand> list(Integer start,Integer end);
    Long deleteAll();

}
