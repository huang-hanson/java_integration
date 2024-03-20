package com.xxl.job.executor.service.jobhandler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * @author hanson
 * @date 2024/3/20 16:59
 */
@Component
public class CommandJobHandler2 {

//    /**
//     * 命令行任务
//     * @param
//     * @return
//     */
//    @XxlJob(value = "commandJobHandler2", init = "", destroy = "")
//    public ReturnT<String> commandJobHandlerTest() throws IOException {
//        //用于获取动态传入进来的参数
//        String jobParam = XxlJobHelper.getJobParam();
//        Process exec = Runtime.getRuntime().exec(jobParam);
//        System.out.println("command run success...");
//        return ReturnT.SUCCESS;
//    }


    /**
     * 命令行任务
     * @param
     * @return
     */
    @XxlJob(value = "commandJobHandler2")
    public ReturnT<String> commandJobHandlerTest() throws IOException {
        //用于获取动态传入进来的参数
        String jobParam = XxlJobHelper.getJobParam();
        File file = new File(jobParam);

        System.out.println(String.valueOf(file));
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getCanonicalFile());
//        Process exec = Runtime.getRuntime().exec("cmd /k start " + "www.baidu.com");
        Process exec2 = Runtime.getRuntime().exec("cmd /k start " + file.getCanonicalFile());
        System.out.println("command run success...");
//        System.out.println(exec.getInputStream());
        //D:\系统默认\桌面\test.bat
        return ReturnT.SUCCESS;
    }

}