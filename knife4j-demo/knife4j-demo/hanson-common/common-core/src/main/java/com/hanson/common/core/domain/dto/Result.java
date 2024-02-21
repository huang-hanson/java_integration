package com.hanson.common.core.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hanson.common.core.constant.HttpStatusConstant;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 响应信息主体
 */
@Data
@NoArgsConstructor
public class Result<T> implements Serializable {
    private static final int SUCCESS = 200; //成功
    private static final int ERROR = 10000; //失败
    private static final long serialVersionUID = 1L;

    /**
     * 响应状态码
     */
    private int code = HttpStatusConstant.SUCCESS;

    /**
     * 结果描述
     */
    private String message;

    /**
     * 数据集
     */
    private T data;

    /**
     * 时间戳
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timestamp = new Date();

    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 构建响应体-成功
     *
     * @param <T> 结果集对象类型
     * @return 响应体-成功
     */
    public static <T> Result<T> success() {
        return success(null);
    }

    /**
     * 构建响应体-成功
     *
     * @param data 结果集对象
     * @param <T>  结果集对象类型
     * @return 响应体-成功
     */
    public static <T> Result<T> success(T data) {
        return success("操作成功", data);
    }

    /**
     * 构建响应体-成功
     *
     * @param message  响应信息
     * @param data 结果集对象
     * @param <T>  结果集对象类型
     * @return 响应体-成功
     */
    public static <T> Result<T> success(String message, T data) {
        return new Result<>(HttpStatusConstant.SUCCESS, message, data);
    }

    /**
     * 构建响应体-失败
     *
     * @param <T> 结果集对象类型
     * @return 响应体-失败
     */
    public static <T> Result<T> error() {
        return new Result<>(ERROR, null, null);
    }

    /**
     * 构建响应体-失败
     *
     * @param message 响应信息
     * @param <T> 结果集对象类型
     * @return 响应体-失败
     */
    public static <T> Result<T> error(String message) {
        return new Result<>(ERROR, message, null);
    }

    /**
     * 构建响应体-失败
     *
     * @param code 响应状态
     * @param message  响应信息
     * @param <T>  结果集对象类型
     * @return 响应体-失败
     */
    public static <T> Result<T> error(Integer code, String message) {
        return new Result<>(code, message, null);
    }

    /**
     * 构建响应体-失败
     *
     * @param code 响应状态
     * @param message  响应信息
     * @param <T>  结果集对象类型
     * @return 响应体-失败
     */
    public static <T> Result<T> error(Integer code, String message, T data) {
        return new Result<>(code, message, data);
    }

    /**
     * 响应返回结果
     *
     * @param rows 影响行数
     * @return 操作结果
     */
    public static <T> Result<T> toAjax(int rows)
    {
        return rows > 0 ? Result.success() : Result.error();
    }

}
