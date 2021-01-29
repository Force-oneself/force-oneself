package com.quan.framework.aop.log;

import com.quan.framework.common.bean.ResultBean;
import org.springframework.lang.Nullable;

/**
 * @Description: 日志异常的处理接口
 * @Author heyq
 * @Date 2021-01-10
 **/
public interface LoggerExceptionHandler<T> {

    /**
     * 获取处理异常的Class对象
     * @return Class对象
     */
    Class<T> getExceptionClass();

    /**
     * 如果返回的ResultBean是null,则该处理方法相当于未生效,不会反馈给前端
     *
     * @param e 所需要的处理的异常或者错误
     * @return web统一返回的Bean对象
     */
    @Nullable
    ResultBean<?> handle(Throwable e);
}
