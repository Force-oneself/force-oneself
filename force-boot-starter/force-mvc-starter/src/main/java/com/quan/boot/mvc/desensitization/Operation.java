package com.quan.boot.mvc.desensitization;

/**
 * Operation
 *
 * @author Force-oneself
 * @date 2022-07-03
 */
@FunctionalInterface
public interface Operation {

    /**
     * 脱敏
     *
     * @param content content
     * @param maskChar maskChar
     * @return  /
     */
    String mask(String content);

}
