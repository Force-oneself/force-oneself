package com.quan.demo.freemarker.base;

import java.util.Collection;

/**
 * @author Force-oneself
 * @description JavaDomainModel
 * @date 2022-03-17
 */
public class JavaDomainModel extends ClassModel {

    private Collection<? extends ClassModel> fields;

    public Collection<? extends ClassModel> getFields() {
        return fields;
    }

    public void setFields(Collection<? extends ClassModel> fields) {
        this.fields = fields;
    }
}
