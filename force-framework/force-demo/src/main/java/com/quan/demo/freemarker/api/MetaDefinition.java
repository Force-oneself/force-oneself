package com.quan.demo.freemarker.api;

import com.quan.demo.freemarker.enums.StringPool;

import java.util.Collections;
import java.util.Set;

/**
 * Meta     元信息接口
 *
 * @author Force-oneself
 * @date 2022-04-27
 */
public interface MetaDefinition {

    /**
     * package路径
     *
     * @return 路径名
     */
    String getPkg();

    /**
     * 元数据类型
     *
     * @return 类型
     */
    String getType();

    /**
     * 元数据注解
     *
     * @return 注解名
     */
    default Set<String> getAnnotations() {
        return Collections.emptySet();
    }

    /**
     * 元信息描述
     *
     * @return 描述
     */
    default String getDescribe() {
        return StringPool.EMPTY;
    }
}
