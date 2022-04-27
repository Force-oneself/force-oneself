package com.quan.demo.freemarker.api;

import java.util.Collections;
import java.util.Set;

/**
 * Meta     元信息接口
 *
 * @author Force-oneself
 * @date 2022-04-27
 */
public interface Meta {

    String EMPTY_STR = "";

    /**
     * 元数据类型
     *
     * @return 类型
     */
    String type();

    /**
     * 元数据注解
     *
     * @return 注解名
     */
    default Set<String> annotations() {
        return Collections.emptySet();
    }

    /**
     * 元信息描述
     *
     * @return 描述
     */
    default String describe() {
        return EMPTY_STR;
    }
}
