package com.quan.demo.freemarker.base;

import com.quan.demo.freemarker.api.ClassMetaDefinition;
import com.quan.demo.freemarker.api.FieldMetaDefinition;
import com.quan.demo.freemarker.enums.StringPool;

import java.util.Collections;
import java.util.Set;

/**
 * SimpleClassMeta
 *
 * @author Force-oneself
 * @date 2022-04-28
 */
public class DefaultClassMeta extends DefaultMeta implements ClassMetaDefinition {

    protected String extend = StringPool.EMPTY;
    protected Set<String> imports = Collections.emptySet();
    protected Set<String> implement = Collections.emptySet();
    protected Set<? extends FieldMetaDefinition> fields = Collections.emptySet();


    @Override
    public Set<String> getImports() {
        return this.imports;
    }

    @Override
    public String getExtend() {
        return this.extend;
    }

    @Override
    public Set<String> getImplement() {
        return this.implement;
    }

    @Override
    public Set<? extends FieldMetaDefinition> getFields() {
        return this.fields;
    }

    public void setExtend(String extend) {
        this.extend = extend;
    }


    public void setImports(Set<String> imports) {
        this.imports = imports;
    }

    public void setImplement(Set<String> implement) {
        this.implement = implement;
    }


    public void setFields(Set<? extends FieldMetaDefinition> fields) {
        this.fields = fields;
    }
}
