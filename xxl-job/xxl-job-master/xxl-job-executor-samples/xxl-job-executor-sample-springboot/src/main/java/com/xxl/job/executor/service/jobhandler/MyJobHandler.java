package com.xxl.job.executor.service.jobhandler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

/**
 * @author hanson
 * @date 2024/3/20 14:39
 */
@Component
public class MyJobHandler {

    /**
     * 简单的任务示例
     *
     * @param param
     * @return
     * @throws InterruptedException
     */
    @XxlJob(value = "myJobHandler", init = "", destroy = "")
    public ReturnT<String> demoJobHandler(String param) throws InterruptedException {
        // 模拟业务的执行
        System.out.println("we should fight...");
        // 返回执行结果
        return ReturnT.SUCCESS;
    }
}
