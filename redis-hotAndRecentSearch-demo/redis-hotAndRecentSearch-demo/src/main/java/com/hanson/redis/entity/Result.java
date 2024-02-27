package com.hanson.redis.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Slf4j
public class Result {

    private int code;
    private String msg;
    private Object data;

    public static int SUCCESS_CODE = 200;
    public static int ERROR_CODE = 300;

    public static String SUCCESS_MSG = "Success";
    public static String ERROR_MSG = "Error";

    public static Result success() {
        return Result.builder()
                .code(SUCCESS_CODE)
                .msg(SUCCESS_MSG)
                .build();
    }

    public static Result success(Object data) {
        return Result.builder()
                .code(SUCCESS_CODE)
                .msg(SUCCESS_MSG)
                .data(data)
                .build();
    }

    public static Result error() {
        return Result.builder()
                .code(ERROR_CODE)
                .msg(ERROR_MSG)
                .build();
    }

    public static Result error(int code, String msg) {
        return Result.builder()
                .code(code)
                .msg(msg)
                .build();
    }
}
