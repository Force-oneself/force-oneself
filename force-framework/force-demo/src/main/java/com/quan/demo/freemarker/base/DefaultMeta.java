package com.quan.demo.freemarker.base;

import com.quan.demo.freemarker.api.MetaDefinition;

import java.util.Collections;
import java.util.Set;

/**
 * SimpleMeta
 *
 * @author Force-oneself
 * @date 2022-04-28
 */
public class DefaultMeta implements MetaDefinition {

    protected Set<String> annotations = Collections.emptySet();
    protected String describe = EMPTY_STR;
    protected String type = EMPTY_STR;
    protected String pkg = EMPTY_STR;

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String getPkg() {
        return this.pkg;
    }

    @Override
    public Set<String> getAnnotations() {
        return this.annotations;
    }

    @Override
    public String getDescribe() {
        return this.describe;
    }

    public void setAnnotations(Set<String> annotations) {
        this.annotations = annotations;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPkg(String pkg) {
        this.pkg = pkg;
    }
}
