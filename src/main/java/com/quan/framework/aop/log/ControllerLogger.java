package com.quan.framework.aop.log;

import com.quan.framework.common.bean.ResultBean;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author heyq
 * @Date 2021-01-09
 **/
@Slf4j
@Component
@Order(0)
@Aspect
public class ControllerLogger {

    /**
     * 日志异常处理类集合
     */
    private List<LoggerExceptionHandler> handlers;

    /**
     * 所有返回ResultBean的方法都作为切入点
     */
    @Pointcut("execution(public com.quan.framework.common.bean.ResultBean *(..))")
    public void controllerLog() {
    }

    /**
     * Controller层日志切面处理
     *
     * @param pjp 处理中间切入点
     * @return 统一bean
     */
    @Around("controllerLog()")
    public Object handlerControllerMethod(ProceedingJoinPoint pjp) {
        long startTime = System.currentTimeMillis();
        ResultBean result;
        try {
            result = (ResultBean) pjp.proceed();
            log.info("{} cost time: {}", pjp.getSignature(), (System.currentTimeMillis() - startTime));
        } catch (Throwable e) {
            result = handlerException(pjp, e);
        }
        return result;
    }

    /**
     * 异常通用处理
     *
     * @param pjp 处理中间切入点
     * @param e
     * @return 统一bean
     */
    private ResultBean<?> handlerException(ProceedingJoinPoint pjp, Throwable e) {
        log.error(pjp.getSignature() + " error ", e);
        List<ResultBean<?>> resultBeans = handlers.stream()
                .filter(handler -> handler.getExceptionClass() == e.getClass())
                .map(handler -> handler.handle(e))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        if (resultBeans.isEmpty()) {
            //TODO 未知的异常，应该格外注意，可以发送邮件通知等
            return new ResultBean(e);
        }
        return resultBeans.get(0);
    }

    @Autowired
    public void setHandlers(List<LoggerExceptionHandler> handlers) {
        this.handlers = handlers;
    }
}
