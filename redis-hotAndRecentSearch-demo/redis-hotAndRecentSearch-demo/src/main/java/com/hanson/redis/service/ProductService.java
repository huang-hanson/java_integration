package com.hanson.redis.service;

import com.hanson.redis.entity.Product;
import com.hanson.redis.entity.UserRecentSearch;
import com.hanson.redis.utils.SearchRedisHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ProductService {

    @Resource
    private SearchRedisHelper searchRedisHelper;

    /**
     * 搜索
     * @param query
     * @return
     */
    public List<Product> search(String query) {
        //业务代码可用es.....此处略过....模拟数据库数据
        List<Product> productList = new ArrayList();
        Product product = new Product();
        product.setId(1L);
        product.setProductName("iphone15");
        productList.add(product);
        searchRedisHelper.addRedisRecentSearch(query);
        searchRedisHelper.addRedisHotSearch(productList);
        return productList;
    }

    /**
     * 热搜列表
     * @return
     */
    public Set<Product> listHotSearch() {
        return searchRedisHelper.listHotSearch();
    }

    /**
     * 最近搜索列表
     * @return
     */
    public Set<UserRecentSearch> listRecentSearch() {
        return searchRedisHelper.listRecentSearch();
    }
}

