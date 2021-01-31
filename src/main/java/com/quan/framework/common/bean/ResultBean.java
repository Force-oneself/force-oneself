package com.quan.framework.common.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Author heyq
 * @Date 2021-01-09
 **/
@Data
public class ResultBean<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int SUCCESS = 0;

    public static final int ERROR = 1;

    private int code = SUCCESS;

    private T data;

    private String msg = "success";

    public ResultBean() {
        super();
    }

    public ResultBean(T data) {
        super();
        this.data = data;
    }

    public ResultBean(Throwable e) {
        super();
        this.code = ERROR;
        this.msg = e.toString();
    }
}
