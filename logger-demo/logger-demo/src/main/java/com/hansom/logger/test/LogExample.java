package com.hansom.logger.test;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName LogExample
 * @Description TODO
 * @date 2024/7/22 9:40
 **/
public class LogExample {
    private static final org.slf4j.Logger hwrtaTraceLogger = org.slf4j.LoggerFactory.getLogger(LogExample.class);

    public static void traceHwRtaTrace(String msg) {
        try {
            hwrtaTraceLogger.trace(msg);
        } catch (Exception e) {
            org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(LogExample.class);
            log.error("记录华为RTA 监测链接跟踪请求的相关数据TRACE日志出错，数据：{}，错误原因：{}", msg, e);
        }
    }

    public static void main(String[] args) {
        traceHwRtaTrace("This is a trace message for testing.");
    }
}