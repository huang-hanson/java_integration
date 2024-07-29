package com.hanson.es.service;

/**
 * @author hanson.huang
 * @version V1.0
 * @InterfaceName IESService
 * @Description TODO
 * @date 2024/7/22 20:23
 **/
public interface IESService {

    /**
     * @param clazz
     * @return
     */
    boolean createEsIndex(Class<?> clazz);
}
