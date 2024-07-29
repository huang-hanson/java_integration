package com.hanson.es.service.impl;

import com.hanson.es.service.IESService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName ESServiceImpl
 * @Description TODO
 * @date 2024/7/22 20:24
 **/
@Slf4j
@Service
public class ESServiceImpl implements IESService {

    @Resource(name = "irisElasticsearchRestTemplate")
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public boolean createEsIndex(Class<?> clazz) {
        try {
            IndexOperations indexOperations = elasticsearchRestTemplate.indexOps(clazz);
            if (!indexOperations.exists()) {
                indexOperations.create(indexOperations.createSettings(clazz), indexOperations.createMapping(clazz));
            }
        } catch (Exception e) {
            log.error("根据类型-{}创建对应的ES索引失败，失败原因：e-{}", clazz.getName(), e);
        }
        return true;
    }
}