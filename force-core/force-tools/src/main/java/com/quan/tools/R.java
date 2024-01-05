package com.quan.tools;

import com.quan.tools.constant.Code;

import java.io.Serializable;

/**
 * @author Force-oneself
 * @date 2023-09-25
 */
public class R<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean success = true;

    private int code;

    private T data;

    private String msg;

    private Long timestamp = System.currentTimeMillis();

    public R() {
        this.code = Code.OK.getCode();
        this.msg = Code.OK.getMsg();
    }

    public R(T data) {
        this();
        this.data = data;
    }

    public R(Throwable e) {
        this.success = false;
        this.code = Code.ERROR.getCode();
        this.msg = e.getMessage();
    }

    public static <T> R<T> ok() {
        return new R<T>();
    }

    public static <T> R<T> ok(T data) {
        return new R<T>(data);
    }

    public static R<?> fail() {
        return fail(null, Code.ERROR.getCode(), Code.ERROR.getMsg());
    }

    public static R<?> fail(int code, String msg) {
        return fail(null, code, msg);
    }

    public static <T> R<T> fail(T data) {
        return fail(data, Code.ERROR);
    }

    public static <T> R<T> fail(T data, Code failCode) {
        return fail(data, failCode.getCode(), failCode.getMsg());
    }

    public static R<?> fail(Code failCode) {
        return fail(null, failCode);
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
