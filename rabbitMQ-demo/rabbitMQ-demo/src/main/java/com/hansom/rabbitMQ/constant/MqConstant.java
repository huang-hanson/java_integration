package com.hansom.rabbitMQ.constant;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName MqConstant
 * @Description TODO
 * @date 2024/7/22 14:03
 **/
public class MqConstant {
    public static final String REQUEST_SUCCESS_STATUS = "1";

    public static final String REQUEST_EHIRE_DELETE_MONGO_RESUME_TYPE = "delete";
    public static final String REQUEST_EHIRE_UPDATE_MONGO_RESUME_TYPE = "update";

    public static final String QUEUE_SENSORS_USER_TYPE_PROJECT_PRODUCTION = "sensors_user_production";
    public static final String QUEUE_SENSORS_USER_TYPE_PROJECT_CAMPUS = "sensors_user_campus";
    public static final String ENV_DEV = "dev";
    public static final String ENV_PROD = "prod";
}