package com.hanson.common.core.constant;

/**
 * 返回状态码
 */
public class HttpStatusConstant {
    /**
     * 操作成功
     */
    public static final int SUCCESS = 200;

    /**
     * 对象创建成功
     */
    public static final int CREATED = 201;

    /**
     * 请求已经被接受
     */
    public static final int ACCEPTED = 202;

    /**
     * 操作已经执行成功，但是没有返回数据
     */
    public static final int NO_CONTENT = 204;

    /**
     * 资源已被移除
     */
    public static final int MOVED_PERM = 301;

    /**
     * 重定向
     */
    public static final int SEE_OTHER = 303;

    /**
     * 资源没有被修改
     */
    public static final int NOT_MODIFIED = 304;

    /**
     * 参数列表错误（缺少，格式不匹配）
     */
    public static final int BAD_REQUEST = 400;

    /**
     * 未授权
     */
    public static final int UNAUTHORIZED = 401;

    /**
     * 访问受限，授权过期
     */
    public static final int FORBIDDEN = 403;

    /**
     * 资源，服务未找到
     */
    public static final int NOT_FOUND = 404;

    /**
     * 不允许的http方法
     */
    public static final int BAD_METHOD = 405;

    /**
     * 资源冲突，或者资源被锁
     */
    public static final int CONFLICT = 409;

    /**
     * 不支持的数据，媒体类型
     */
    public static final int UNSUPPORTED_TYPE = 415;

    /**
     * 系统内部错误
     */
    public static final int ERROR = 500;

    /**
     * 接口未实现
     */
    public static final int NOT_IMPLEMENTED = 501;

    /**
     * 参数为空
     */
    public static final int PARAM_IS_NULL = 319;

    /**
     * 数据源连接失败
     */
    public static final int DATA_SOURCE_CONNECTION_FAILED = 4001;

    /**
     * 数据源类型不支持
     */
    public static final int DATA_SOURCE_TYPE_DOES_NOT_MATCH_TEMPORARILY = 4002;

    /**
     * SQL 执行异常
     */
    public static final int EXECUTE_SQL_ERROR = 4003;

    /**
     * 不完整参数替换值
     */
    public static final int INCOMPLETE_PARAMETER_REPLACEMENT_VALUES = 4004;

    /**
     * 执行 JS 异常
     */
    public static final int EXECUTE_JS_ERROR = 4005;

    /**
     * 分析数据错误
     */
    public static final int ANALYSIS_DATA_ERROR = 4006;

    /**
     * 类找不到
     */
    public static final int CLASS_NOT_FOUND = 4007;

    /**
     * 规则参数校验不通过
     */
    public static final int RULE_FIELDS_CHECK_ERROR = 4008;
}
