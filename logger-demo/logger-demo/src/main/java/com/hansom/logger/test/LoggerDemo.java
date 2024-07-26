package com.hansom.logger.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName LoggerDemo
 * @Description TODO
 * @date 2024/7/22 9:36
 **/
public class LoggerDemo {

    private static final Logger hwrtaTraceLogger = LoggerFactory.getLogger("hwrtaTraceLogger");
    private static final Logger log = LoggerFactory.getLogger(LoggerDemo.class);

    public static void traceHwRtaTrace(String msg) {
        try {
            hwrtaTraceLogger.trace(msg);
        } catch (Exception e) {
            log.error("记录华为RTA 监测链接跟踪请求的相关数据TRACE日志出错，数据：{}，错误原因：{}", msg, e);
        }
    }

    public static void main(String[] args) {
        // 模拟记录API请求信息
        String apiRequestDetails = "API Request: GET /api/v1/users, Params: {userId: 123}";
        traceHwRtaTrace(apiRequestDetails);
    }
}