package com.miao.boot.blog.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Accessors;
import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * @title: Result
 * @description: rest接口公共返回封装
 * @author: dengmiao
 * @create: 2019-07-01 16:27
 **/
@Data
@Accessors(chain = true)
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 成功标志
     */
    private boolean success;

    /**
     * 返回处理消息
     */
    private String message;

    /**
     * 返回代码
     */
    private Integer code;

    /**
     * 返回数据对象 data
     */
    private T result;

    /**
     * 时间戳
     */
    private long timestamp = System.currentTimeMillis();

    @JsonIgnore
    public static final HttpStatus OK = HttpStatus.OK;

    public static transient final HttpStatus NOTFOUND = HttpStatus.NOT_FOUND;

    @JsonIgnore
    public static transient final HttpStatus ERROR = HttpStatus.INTERNAL_SERVER_ERROR;

    public void error500(String message) {
        this.message = message;
        this.code = ERROR.value();
        this.success = false;
    }

    public void success(String message) {
        this.message = message;
        this.code = OK.value();
        this.success = true;
    }

    public static Result<?> notFound(String msg) {
        return error(NOTFOUND.value(), msg);
    }

    public static Result<Object> error(String msg) {
        return error(ERROR.value(), msg);
    }

    public static Result<Object> error(int code, String msg) {
        Result<Object> r = new Result<>();
        r.setCode(code);
        r.setMessage(msg);
        r.setSuccess(false);
        return r;
    }

    public static Result<Object> ok(String msg) {
        Result<Object> r = new Result<>();
        r.setSuccess(true);
        r.setCode(OK.value());
        r.setMessage(msg);
        return r;
    }

    public static Result<Object> ok(Object obj) {
        Result<Object> r = new Result<>();
        r.setSuccess(true);
        r.setCode(OK.value());
        r.setMessage(OK.getReasonPhrase());
        r.setResult(obj);
        return r;
    }

    public static Result ok(Publisher obj) {
        Result<Publisher> r = new Result<>();
        r.setSuccess(true);
        r.setCode(OK.value());
        r.setMessage(OK.getReasonPhrase());
        r.setResult(obj);
        return r;
    }

    public static Result<Void> ok() {
        Result<Void> r = new Result<>();
        r.setSuccess(true);
        r.setCode(OK.value());
        r.setMessage(OK.getReasonPhrase());
        return r;
    }
}
