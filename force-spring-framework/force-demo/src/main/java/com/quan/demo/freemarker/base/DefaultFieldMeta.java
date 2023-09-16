package com.quan.demo.freemarker.base;

import com.quan.demo.freemarker.api.FieldMetaDefinition;

import java.util.Objects;

/**
 * SimpleClassMeta
 *
 * @author Force-oneself
 * @date 2022-04-28
 */
public class DefaultFieldMeta extends DefaultMeta implements FieldMetaDefinition {

    protected String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DefaultFieldMeta fieldMeta = (DefaultFieldMeta) o;
        return Objects.equals(name, fieldMeta.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
