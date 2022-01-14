package com.quan.framework.mongo.builder;


import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.Collection;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

/**
 * @author Force-oneself
 * @description CriteriaBuilder
 * @date 2022-01-12
 */
public class Criterias {

    private Criteria criteria;

    public static Criterias of() {
        return new Criterias();
    }

    public static Criterias of(Criteria criteria) {
        return new Criterias(criteria);
    }

    public Criterias() {
        criteria = new Criteria();
    }

    public Criterias(Criteria criteria) {
        this.criteria = criteria == null ? new Criteria() : criteria;
    }

    public Criteria criteria() {
        return this.criteria;
    }

    public Criterias and(boolean cond, UnaryOperator<Criteria> operator) {
        if (cond) {
            this.criteria = operator.apply(this.criteria);
        }
        return this;
    }

    public Criterias ne(boolean cond, String key, Object value) {
        return this.and(cond, cri -> cri.and(key).ne(value));
    }

    public Criterias ne(String key, Object value) {
        return this.ne(true, key, value);
    }

    public Criterias is(boolean cond, String key, Object value) {
        return this.and(cond, cri -> cri.and(key).is(value));
    }

    public Criterias is(String key, Object value) {
        return this.is(true, key, value);
    }

    public Criterias lt(boolean cond, String key, Object value) {
        return this.and(cond, cri -> cri.and(key).lt(value));
    }

    public Criterias lt(String key, Object value) {
        return this.lt(true, key, value);
    }

    public Criterias lte(boolean cond, String key, Object value) {
        return this.and(cond, cri -> cri.and(key).lte(value));
    }

    public Criterias lte(String key, Object value) {
        return this.lte(true, key, value);
    }

    public Criterias gt(boolean cond, String key, Object value) {
        return this.and(cond, cri -> cri.and(key).gt(value));
    }

    public Criterias gt(String key, Object value) {
        return this.gt(true, key, value);
    }

    public Criterias gte(boolean cond, String key, Object value) {
        return this.and(cond, cri -> cri.and(key).gte(value));
    }

    public Criterias gte(String key, Object value) {
        return this.gte(true, key, value);
    }

    public Criterias bte(boolean cond, String key, Object gte, Object lte) {
        return this.and(cond, cri -> cri.and(key).gte(gte).lte(lte));
    }

    public Criterias bte(String key, Object gte, Object lte) {
        return this.bte(true, key, gte, lte);
    }

    public Criterias bt(boolean cond, String key, Object gte, Object lte) {
        return this.and(cond, cri -> cri.and(key).gt(gte).lt(lte));
    }

    public Criterias bt(String key, Object gte, Object lte) {
        return this.bt(true, key, gte, lte);
    }

    public Criterias btl(boolean cond, String key, Object gte, Object lte) {
        return this.and(cond, cri -> cri.and(key).gt(gte).lte(lte));
    }

    public Criterias btl(String key, Object gte, Object lte) {
        return this.btl(true, key, gte, lte);
    }

    public Criterias btAuto(boolean cond, String key, Object gte, Object lte) {
        if (gte == null && lte == null) {
            return this;
        }
        if (gte != null && lte != null) {
            return this.and(cond, cri -> cri.and(key).gt(gte).lt(lte));
        }
        if (gte != null) {
            return this.and(cond, cri -> cri.and(key).gt(gte));
        }
        return this.and(cond, cri -> cri.and(key).lt(lte));
    }

    public Criterias btAuto(String key, Object gte, Object lte) {
        return this.btAuto(true, key, gte, lte);
    }

    public Criterias bteAuto(boolean cond, String key, Object gte, Object lte) {
        if (gte == null && lte == null) {
            return this;
        }
        if (gte != null && lte != null) {
            return this.and(cond, cri -> cri.and(key).gte(gte).lte(lte));
        }
        if (gte != null) {
            return this.and(cond, cri -> cri.and(key).gte(gte));
        }
        return this.and(cond, cri -> cri.and(key).lte(lte));
    }

    public Criterias bteAuto(String key, Object gte, Object lte) {
        return this.bteAuto(true, key, gte, lte);
    }

    public Criterias btlAuto(boolean cond, String key, Object gte, Object lte) {
        if (gte == null && lte == null) {
            return this;
        }
        if (gte != null && lte != null) {
            return this.and(cond, cri -> cri.and(key).gt(gte).lte(lte));
        }
        if (gte != null) {
            return this.and(cond, cri -> cri.and(key).gt(gte));
        }
        return this.and(cond, cri -> cri.and(key).lte(lte));
    }

    public Criterias btlAuto(String key, Object gte, Object lte) {
        return this.btlAuto(true, key, gte, lte);
    }

    public Criterias btgAuto(boolean cond, String key, Object gte, Object lte) {
        if (gte == null && lte == null) {
            return this;
        }
        if (gte != null && lte != null) {
            return this.and(cond, cri -> cri.and(key).gte(gte).lt(lte));
        }
        if (gte != null) {
            return this.and(cond, cri -> cri.and(key).gte(gte));
        }
        return this.and(cond, cri -> cri.and(key).lt(lte));
    }

    public Criterias btgAuto(String key, Object gte, Object lte) {
        return this.btgAuto(true, key, gte, lte);
    }

    public Criterias btg(boolean cond, String key, Object gte, Object lte) {
        return this.and(cond, cri -> cri.and(key).gte(gte).lt(lte));
    }

    public Criterias btg(String key, Object gte, Object lte) {
        return this.btg(true, key, gte, lte);
    }

    public Criterias elemMatch(boolean cond, String key, Criteria criteria) {
        return this.and(cond, cri -> cri.and(key).elemMatch(criteria));
    }

    public Criterias elemMatch(String key, Criteria criteria) {
        return this.elemMatch(true, key, criteria);
    }

    public Criterias regex(boolean cond, String key, String regex) {
        return this.and(cond, cri -> cri.and(key).regex(regex));
    }

    public Criterias regex(String key, String regex) {
        return this.regex(true, key, regex);
    }

    public Criterias regex(boolean cond, String key, Pattern pattern) {
        return this.and(cond, cri -> cri.and(key).regex(pattern));
    }

    public Criterias regex(String key, Pattern regex) {
        return this.regex(true, key, regex);
    }

    public Criterias regex(boolean cond, String key, String regex, String options) {
        return this.and(cond, cri -> cri.and(key).regex(regex, options));
    }

    public Criterias regex(String key, String regex, String options) {
        return this.regex(true, key, regex, options);
    }

    public Criterias not(boolean cond, String key) {
        return this.and(cond, cri -> cri.and(key).not());
    }

    public Criterias not(String key) {
        return this.not(true, key);
    }

    public Criterias in(boolean cond, String key, Collection<?> value) {
        return this.and(cond, cri -> cri.and(key).in(value));
    }

    public Criterias in(String key, Collection<?> value) {
        return this.in(true, key, value);
    }

    public Criterias nin(boolean cond, String key, Collection<?> value) {
        return this.and(cond, cri -> cri.and(key).nin(value));
    }

    public Criterias nin(String key, Collection<?> value) {
        return this.nin(true, key, value);
    }

    public Criterias all(boolean cond, String key, Collection<?> value) {
        return this.and(cond, cri -> cri.and(key).all(value));
    }

    public Criterias all(String key, Collection<?> value) {
        return this.all(true, key, value);
    }

    public Criterias size(boolean cond, String key, int size) {
        return this.and(cond, cri -> cri.and(key).size(size));
    }

    public Criterias size(String key, int size) {
        return this.size(true, key, size);
    }

    public Criterias exists(boolean cond, String key, boolean exists) {
        return this.and(cond, cri -> cri.and(key).exists(exists));
    }

    public Criterias exists(String key, boolean exists) {
        return this.exists(true, key, exists);
    }

    public Criterias exists(String key) {
        return this.exists(key, true);
    }

    public Criterias type(boolean cond, String key, int type) {
        return this.and(cond, cri -> cri.and(key).type(type));
    }

    public Criterias type(String key, int type) {
        return this.type(true, key, type);
    }

    public Criterias alike(boolean cond, String key, Example<?> sample) {
        return this.and(cond, cri -> cri.and(key).alike(sample));
    }

    public Criterias alike(String key, Example<?> sample) {
        return this.alike(true, key, sample);
    }


}
