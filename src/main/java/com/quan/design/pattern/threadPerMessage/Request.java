package com.quan.design.pattern.threadPerMessage;

/**
 * 提交任何业务受理请求会被封装成Request对象
 */
public class Request {

    private final String business;

    public Request(String business) {
        this.business = business;
    }

    @Override
    public String toString() {
        return business;
    }
}
