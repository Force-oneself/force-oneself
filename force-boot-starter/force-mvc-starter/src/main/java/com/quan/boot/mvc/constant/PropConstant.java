package com.quan.boot.mvc.constant;

/**
 * 配置前缀常量
 *
 * @author Force-oneself
 * @date 2023-09-08
 */
public interface PropConstant {

    /**
     * 开发者标识
     */
    String DEVELOPER = "force";

    /**
     * 应用标识
     */
    String MVC = DEVELOPER + ".mvc";

    /**
     * 加解密
     */
    String CRYPTO = MVC + ".crypto";

    /**
     * RSA加解密配置
     */
    String RSA = CRYPTO + ".rsa";

    /**
     * XSS
     */
    String XSS = MVC + ".xss";

    /**
     * 日志
     */
    String LOG = MVC + ".log";

    /**
     * servlet 重复读流
     */
    String REPEAT = MVC + ".repeat";

    /**
     * 异常
     */
    String EXCEPTION = MVC + ".exception";

}
