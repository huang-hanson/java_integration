package com.hanson.cache.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hanson.cache.entity.Brand;

/**
 * @author hanson
 * @date 2024/3/18 15:03
 */
public interface BrandService extends IService<Brand> {

    Brand getById(Integer id);
}
