package com.quan.common.constant;

/**
 * @author Force-oneself
 * @Description Code.java
 * @date 2021-07-27
 */
public enum Code {

    /**
     * 成功
     */
    OK(200, "success"),
    ERROR(500, "系统异常！");

    private Integer code;
    private String msg;

    Code(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
