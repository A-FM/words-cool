package com.wordscool.utils;

import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;

// 通用结果返回类
@Data
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    // 状态码
    private int code;
    // 消息
    private String message;
    // 数据
    private T data;
    // 操作时间
    private Date timestamp;
    // 分页信息
    private PageInfo pageInfo;

    public Result() {
        this.timestamp = new Date();
    }

    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.timestamp = new Date();
    }

    // 成功返回结果
    public static <T> Result<T> success(T data) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    // 成功返回结果，自定义消息
    public static <T> Result<T> success(String message, T data) {
        return new Result<>(ResultCode.SUCCESS.getCode(), message, data);
    }

    // 成功返回结果，带分页信息
    public static <T> Result<T> success(T data, PageInfo pageInfo) {
        Result<T> result = success(data);
        result.setPageInfo(pageInfo);
        return result;
    }

    // 失败返回结果
    public static <T> Result<T> fail(ResultCode resultCode) {
        return new Result<>(resultCode.getCode(), resultCode.getMessage(), null);
    }

    // 失败返回结果，自定义消息
    public static <T> Result<T> fail(ResultCode resultCode, String message) {
        return new Result<>(resultCode.getCode(), message, null);
    }

    // 失败返回结果，自定义状态码和消息
    public static <T> Result<T> fail(int code, String message) {
        return new Result<>(code, message, null);
    }

    // 分页信息类
    @Getter
    public static class PageInfo {
        private final int pageNum;
        private final int pageSize;
        private final long total;

        public PageInfo(int pageNum, int pageSize, long total) {
            this.pageNum = pageNum;
            this.pageSize = pageSize;
            this.total = total;
        }

    }

    // 状态码枚举类
    @Getter
    public enum ResultCode {
        SUCCESS(200, "操作成功"),
        FAIL(500, "操作失败"),
        UNAUTHORIZED(401, "未授权"),
        FORBIDDEN(403, "禁止访问"),
        NOT_FOUND(404, "资源未找到"),
        PARAM_ERROR(400, "参数错误");

        private final int code;
        private final String message;

        ResultCode(int code, String message) {
            this.code = code;
            this.message = message;
        }

    }
}