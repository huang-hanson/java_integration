package com.hansom.rabbitMQ.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName MessageActiveTaskReportDTO
 * @Description TODO
 * @date 2024/7/22 14:11
 **/
@Data
public class MessageActiveTaskReportDTO {
    /**
     * 执行日志列表
     */
    private List<MessageChildTaskTaskInfoDTO> logList;

    /**
     * 活动推送子任务信息
     */
    @Data
    public static class MessageAppTaskInfoDTO {
        /**
         * 活动推送子任务id
         */
        private Long id;

        /**
         * 活动推送子任务名称
         */
        private String name;

        /**
         * 推送运行日志列表
         */
        private List<MessageChildTaskTaskInfoDTO> logList;
    }

    /**
     * 推送运行日志
     */
    @Data
    public static class MessageChildTaskTaskInfoDTO {
        /**
         * 日志id
         */
        private Long id;

        /**
         * 活动推送主任务id
         */
        private Long activeTaskId;


        /**
         * 活动推送主任务名称
         */
        private String activeTaskName;

        /**
         * 活动推送子任务id
         */
        private Long appTaskId;

        /**
         * 活动推送子任务名称
         */
        private String appTaskName;

        /**
         * 推送平台类型
         * 51jobAppPush，51app
         * xyAppPush，前程无忧学生版app
         * yjsAppPush，应届生app
         */
        private String type;

        /**
         * 计划推送数
         */
        private Long pushCount;

        /**
         * 运行开始时间
         */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime startTime;

        /**
         * 运行结束时间
         */
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime endTime;
    }
}
