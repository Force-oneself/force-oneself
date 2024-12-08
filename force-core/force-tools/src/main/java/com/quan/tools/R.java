package com.quan.tools;

import com.quan.tools.constant.Code;

import java.io.Serializable;

/**
 * Controller 统一返回结果对象
 *
 * @author Force-oneself
 * @date 2023-09-25
 */
public class R<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 是否成功
     */
    private boolean success = true;

    /**
     * 状态码
     */
    private int code;

    /**
     * 数据
     */
    private T data;

    /**
     * 消息
     */
    private String msg;

    /**
     * 时间戳
     */
    private Long timestamp = System.currentTimeMillis();

    private R() {
        this.code = Code.OK.getCode();
        this.msg = Code.OK.getMsg();
    }

    private R(T data) {
        this();
        this.data = data;
    }

    private R(Throwable e) {
        this.success = false;
        this.code = Code.ERROR.getCode();
        this.msg = e.getMessage();
    }

    public static <T> R<T> ok() {
        return new R<>();
    }

    public static <T> R<T> ok(T data) {
        return new R<>(data);
    }

    public static <T> R<T> fail() {
        return fail(null, Code.ERROR.getCode(), Code.ERROR.getMsg());
    }

    public static <T> R<T> fail(T data) {
        return fail(data, Code.ERROR.getCode(), Code.ERROR.getMsg());
    }

    public static <T> R<T> fail(int code, String msg) {
        return fail(null, code, msg);
    }

    public static <T> R<T> fail(T data, int code, String msg) {
        return new R<T>().code(code).msg(msg).data(data).success(false);
    }

    public R<T> success(boolean success) {
        this.success = success;
        return this;
    }

    public R<T> code(int code) {
        this.code = code;
        return this;
    }

    public R<T> msg(String msg) {
        this.msg = msg;
        return this;
    }

    public R<T> data(T data) {
        this.data = data;
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}