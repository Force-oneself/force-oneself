package com.quan.demo.freemarker.support.mysql.meta;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Force-oneself
 * @description DefaultColumnMeta
 * @date 2022-01-03
 */
public class DefaultColumnMeta implements ColumnMeta {

    private final Map<String, Object> columnMap = new HashMap<>(16);

    @Override
    public Map<String, Object> metaMap() {
        return this.columnMap;
    }
}
