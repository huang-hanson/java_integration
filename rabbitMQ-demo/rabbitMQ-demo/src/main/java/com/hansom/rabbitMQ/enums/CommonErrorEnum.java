package com.hansom.rabbitMQ.enums;

import lombok.Getter;

@Getter
public enum CommonErrorEnum implements ErrorEnum {
    /**
     * 通用异常枚举值
     * @param null
     * @return
     * @author linton.cao
     * @date 2021/9/2 10:54
     */
    SYSTEM_ERROR(100000,"网络超时，请稍后重试！"),
    PARAM_NULL_ERROR(100001,"参数为空异常！"),
    BLOCK_ERROR(100002,"服务不可用，请稍后重试！"),
    SERVICE_UNAVAILABLE_ERROR(100003,"服务不可用，请稍后重试！"),
    PARAM_VALIDATE_ERROR(100004,"参数校验错误"),
    LOCKING_ERROR(100005,"加锁错误"),
    BATCH_LOCKING_ERROR(100006,"批量加锁错误"),
    UNLOCK_ERROR(100007,"解锁错误"),
    BATCH_UNLOCK_ERROR(100008,"批量解锁错误"),
    UNKNOWN_FROM_DOMAIN(100009, "未知fromDomain"),
    DATA_NOT_FOUND(100010,"未查询到数据"),
    GENERATE_SIGN_ERROR(100011,"生成签名时异常"),
    USER_TOKEN_INVALID(110102,"用户认证失败，用户令牌无效"),
    USER_PERMISSION_EXIST(110200,"用户权限已经存在"),
    USER_Mail_ERROR(110201,"用户邮箱格式有误"),
    ;

    private Integer code;

    private String msg;

    @Override
    public String getCodeStr(){
        return this.code.toString();
    }

    CommonErrorEnum(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
