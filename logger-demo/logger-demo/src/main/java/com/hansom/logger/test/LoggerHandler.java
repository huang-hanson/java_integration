package com.hansom.logger.test;

import org.springframework.beans.factory.InitializingBean;


/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName LoggerHandler
 * @Description TODO
 * @date 2024/7/18 17:48
 **/
public class LoggerHandler implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        Calculator calculator = new Calculator();
        calculator.add(1, 2);
        calculator.subtract(1, 2);
    }
}