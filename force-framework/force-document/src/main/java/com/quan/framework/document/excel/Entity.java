package com.quan.framework.document.excel;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * @author Force-oneself
 * @Description Entity.java
 * @date 2021-06-07
 */
public class Entity {

    @ExcelProperty(value = "名字")
    private String name;
    @ExcelProperty(value = "年龄")
    private Integer age;
    @ExcelProperty(value = "描述")
    private String desc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
