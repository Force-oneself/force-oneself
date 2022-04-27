package com.quan.demo.freemarker.support.mysql.meta;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Force-oneself
 * @description ColumnMetadata
 * @date 2021-12-30
 */
public interface ColumnMeta {

    /**
     * 字段坐标
     *
     * @return 名
     */
    default int index() {
        return (int) metaMap().get("COLUMN_INDEX");
    }

    /**
     * 字段名
     *
     * @return 名
     */
    default String name() {
        return (String) metaMap().get("COLUMN_NAME");
    }

    /**
     * 字段类型
     *
     * @return 类型
     */
    default String typeName() {
        return (String) metaMap().get("TYPE_NAME");
    }

    /**
     * 字段描述
     *
     * @return 描述
     */
    default String comment() {
        return (String) metaMap().get("REMARKS");
    }

    /**
     * 原始数据
     *
     * @return java.util.Map<java.lang.String, java.lang.Object>
     */
    default Map<String, Object> metaMap() {
        return new HashMap<>();
    }

    /**
     * 设置列信息
     *
     * @param key   key
     * @param value value
     */
    default void set(String key, Object value) {
        metaMap().put(key, value);
    }

}
