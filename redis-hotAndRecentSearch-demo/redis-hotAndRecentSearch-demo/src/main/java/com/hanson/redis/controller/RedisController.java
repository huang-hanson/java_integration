package com.hanson.redis.controller;

import com.hanson.redis.entity.Result;
import com.hanson.redis.service.ProductService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("/redis/test")
@RestController
public class RedisController {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private ProductService productService;

    /**
     * 删除redis
     * @param key
     * @return
     */
    @GetMapping("/w/remove/redis")
    public Result removeRedis(String key){
        redisTemplate.delete(key);
        return Result.success();
    }

    /**
     * 搜索
     * @param query
     * @return
     */
    @GetMapping("/r/search/product")
    public Result listProduct(String query) {
        return Result.success(productService.search(query));
    }

    /**
     * 热搜列表
     * @return
     */
    @ResponseBody
    @GetMapping("/r/list/hot/search")
    public Result listHotSearch() {
        return Result.success(productService.listHotSearch());
    }

    /**
     * 最近搜索列表
     * @return
     */
    @ResponseBody
    @GetMapping("/r/list/recent/search")
    public Result recentHotSearch() {
        return Result.success(productService.listRecentSearch());
    }

}

