package com.quan.demo.framework.response;

import com.quan.common.bean.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Force-oneself
 * @Description RestControllerExceptionAdvice.java
 * @date 2021-07-27
 */
@RestControllerAdvice
public class RestControllerExceptionAdvice {


    private final Logger log = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public R<String> exception(NullPointerException e) {
        return fail(e);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public R<String> exception(RuntimeException e) {
        return fail(e);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public R<String> exception(Exception e) {
        return fail(e);
    }

    @ExceptionHandler(Error.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public R<String> exception(Error e) {
        return fail(e);
    }

    private R<String> fail(Throwable e) {
        log.error("系统异常", e);
        return R.fail((String)null).msg(e.getMessage());
    }
}
