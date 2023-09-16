package com.quan.demo.freemarker.support.mysql.meta;

import java.util.Collection;

/**
 * @author Force-oneself
 * @description TableMetadata
 * @date 2021-12-30
 */
public interface TableMeta {

    /**
     * 表名
     *
     * @return java.lang.String
     */
    String name();

    /**
     * 列信息
     *
     * @return java.util.Collection<com.quan.demo.freemarker.support.mysql.meta.ColumnMeta>
     */
    Collection<ColumnMeta> columns();

}
