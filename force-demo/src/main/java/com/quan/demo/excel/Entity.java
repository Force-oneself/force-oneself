package com.quan.demo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author Force-oneself
 * @Description Entity.java
 * @date 2021-06-07
 */
@Data
public class Entity {

    @ExcelProperty(value = "名字")
    private String name;
    @ExcelProperty(value = "年龄")
    private Integer age;
    @ExcelProperty(value = "描述")
    private String desc;
}
