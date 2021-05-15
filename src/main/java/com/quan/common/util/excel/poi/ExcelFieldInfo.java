package com.quan.common.util.excel.poi;

import lombok.Data;

/**
 * @Description:
 * @Author heyq
 * @Date 2020-11-15
 **/
@Data
public class ExcelFieldInfo {

    /**
     * 字段值
     */
    private String name;
    /**
     * 字段索引
     */
    private Integer index;
    /**
     * 标题
     */
    private String title;

}
