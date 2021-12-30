package com.quan.demo.freemarker.meta;

/**
 * @author Force-oneself
 * @description ColumnMetadata
 * @date 2021-12-30
 */
public interface ColumnMeta {

    String name();

    int type();

    String comment();

    int length();

    boolean isAutoIncrement();
}
