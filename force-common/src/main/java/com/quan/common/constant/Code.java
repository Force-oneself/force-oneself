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
    ERROR(500, "系统异常！"),
    NULL_POINT(501, "空指针异常"),
    ;

    private final Integer code;
    private final String msg;

    Code(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
