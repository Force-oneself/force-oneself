package com.quan.demo.framework.aop.log;

import com.quan.common.bean.R;
import org.springframework.stereotype.Component;

/**
 * @Description: 测试日志异常处理组件
 * @Author heyq
 * @Date 2021-01-10
 **/
@Component
public class DefaultLoggerExceptionHandler implements LoggerExceptionHandler<NullPointerException>{

    @Override
    public R<?> handle(Throwable e) {

        return null;
    }

    @Override
    public Class<NullPointerException> getExceptionClass() {
        return NullPointerException.class;
    }
}
