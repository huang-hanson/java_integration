package com.hansom.logger.util;


import com.alibaba.excel.util.DateUtils;

import java.time.LocalDate;


/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName DateUtil
 * @Description TODO
 * @date 2024/7/19 13:52
 **/
public class DateUtil {

    public static void main(String[] args) {

        String yesterday = DateUtils.format(LocalDate.now().minusDays(1), "yyyyMMdd");
        System.out.println(yesterday);

    }

}