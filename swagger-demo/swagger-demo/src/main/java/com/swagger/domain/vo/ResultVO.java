package com.swagger.domain.vo;

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
public class ResultVO {

    private int code;
    private String msg;
    private Object data;

    public static int SUCCESS_CODE = 200;
    public static int ERROR_CODE = 300;

    public static String SUCCESS_MSG = "Success";
    public static String ERROR_MSG = "Error";

    public static ResultVO success() {
        return ResultVO.builder()
                .code(SUCCESS_CODE)
                .msg(SUCCESS_MSG)
                .build();
    }

    public static ResultVO success(Object data) {
        return ResultVO.builder()
                .code(SUCCESS_CODE)
                .msg(SUCCESS_MSG)
                .data(data)
                .build();
    }

    public static ResultVO error() {
        return ResultVO.builder()
                .code(ERROR_CODE)
                .msg(ERROR_MSG)
                .build();
    }

    public static ResultVO error(int code, String msg) {
        return ResultVO.builder()
                .code(code)
                .msg(msg)
                .build();
    }
}
