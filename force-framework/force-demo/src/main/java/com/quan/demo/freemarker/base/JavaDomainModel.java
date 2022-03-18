package com.quan.demo.freemarker.base;

import java.util.List;

/**
 * @author Force-oneself
 * @description JavaDomainModel
 * @date 2022-03-17
 */
public class JavaDomainModel extends ClassModel {

    private String name;

    private List<? extends ClassModel> fields;

    public List<? extends ClassModel> getFields() {
        return fields;
    }

    public void setFields(List<? extends ClassModel> fields) {
        this.fields = fields;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
