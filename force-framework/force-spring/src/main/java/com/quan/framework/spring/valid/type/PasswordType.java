package com.quan.framework.spring.valid.type;

/**
 * 密码复杂度
 *
 * @author chenjipdc@gmail.com
 * @date 2021/1/12 9:50 上午
 */
public enum PasswordType {
    /**
     * 简单
     */
    SIMPLE("包含数字或字母或特殊字符(!@#$%^&*)"),

    /**
     * 适中
     */
    MEDIUM("包含数字跟字母或特殊字符(!@#$%^&*)"),

    /**
     * 复杂
     */
    COMPLEX("同时包含数字，字母，特殊符号");

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private String desc;

    PasswordType(String desc) {
        this.desc = desc;
    }
}
