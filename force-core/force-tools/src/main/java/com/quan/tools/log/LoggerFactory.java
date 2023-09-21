package com.quan.tools.log;

/**
 * 日志工具类
 *
 * @author Force-oneself
 * @date 2023-09-21
 */
public class LoggerFactory {

    /**
     * 获取日志记录器
     *
     * @param cls 类
     * @return /
     */
    public static Logger getLogger(Class<?> cls) {
        return getLogger(cls.getName());
    }

    /**
     * 获取日志记录器
     *
     * @param name 日志器名
     * @return /
     */
    public static Logger getLogger(String name) {
        return new Slf4jLogger(org.slf4j.LoggerFactory.getLogger(name));
    }
}
