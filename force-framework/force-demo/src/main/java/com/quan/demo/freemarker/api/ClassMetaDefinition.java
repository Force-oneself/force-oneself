package com.quan.demo.freemarker.api;

import java.util.Collections;
import java.util.Set;

/**
 * ClassMeta 类元信息接口
 *
 * @author Force-oneself
 * @date 2022-04-27
 */
public interface ClassMetaDefinition extends MetaDefinition {

    /**
     * import包
     *
     * @return 倒入包名
     */
    default Set<String> getImports() {
        return Collections.emptySet();
    }

    /**
     * 继承父类
     *
     * @return 父类名
     */
    default String getExtend() {
        return EMPTY_STR;
    }

    /**
     * 实现接口
     *
     * @return 接口名
     */
    default Set<String> getImplement() {
        return Collections.emptySet();
    }

    /**
     * 成员变量
     *
     * @return 成员信息
     */
    default Set<? extends FieldMetaDefinition> getFields() {
        return Collections.emptySet();
    }

}
