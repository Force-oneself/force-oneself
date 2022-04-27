package com.quan.demo.freemarker.api;

import java.util.Collections;
import java.util.Set;

/**
 * ClassMeta 类元信息接口
 *
 * @author Force-oneself
 * @date 2022-04-27
 */
public interface ClassMeta extends Meta {

    /**
     * package路径
     *
     * @return 路径名
     */
    String pkg();

    /**
     * import包
     *
     * @return 倒入包名
     */
    default Set<String> imports() {
        return Collections.emptySet();
    }

    /**
     * 继承父类
     *
     * @return 父类名
     */
    default String extend() {
        return EMPTY_STR;
    }

    /**
     * 实现接口
     *
     * @return 接口名
     */
    default Set<String> implement() {
        return Collections.emptySet();
    }

    /**
     * 成员变量
     *
     * @return 成员信息
     */
    default Set<FieldMeta> fields() {
        return Collections.emptySet();
    }

}
