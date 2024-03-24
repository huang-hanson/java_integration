package com.hanson.mockito.dao;

import com.hanson.mockito.entity.SalesRep;

/**
 * @author hanson
 * @date 2024/3/22 15:27
 */
public class SalesDao {

    public SalesRep findRep(String areaCode, String operatorCode) {
        if ("a".equals(areaCode) && "b".equals(operatorCode)) {
            return new SalesRep("Hanson");
        }
        return null;
    }
}
