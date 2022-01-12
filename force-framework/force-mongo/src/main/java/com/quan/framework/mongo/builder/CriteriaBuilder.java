package com.quan.framework.mongo.builder;


import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.regex.Pattern;

/**
 * @author Force-oneself
 * @description QueryBuilder
 * @date 2022-01-12
 */
public class CriteriaBuilder {

    private final Criteria criteria;

    public CriteriaBuilder() {
        criteria = new Criteria();
    }

    public CriteriaBuilder(Criteria criteria) {
        this.criteria = criteria == null ? new Criteria() : criteria;
    }

    public CriteriaBuilder and(boolean cond, Consumer<Criteria> consumer) {
        if (cond) {
            consumer.accept(this.criteria);
        }
        return this;
    }

    public CriteriaBuilder ne(boolean cond, String key, Object value) {
        return this.and(cond, cri -> cri.and(key).ne(value));
    }

    public CriteriaBuilder ne(String key, Object value) {
        return this.ne(true, key, value);
    }

    public CriteriaBuilder is(boolean cond, String key, Object value) {
        return this.and(cond, cri -> cri.and(key).is(value));
    }

    public CriteriaBuilder is(String key, Object value) {
        return this.is(true, key, value);
    }

    public CriteriaBuilder lt(boolean cond, String key, Object value) {
        return this.and(cond, cri -> cri.and(key).lt(value));
    }

    public CriteriaBuilder lt(String key, Object value) {
        return this.lt(true, key, value);
    }

    public CriteriaBuilder lte(boolean cond, String key, Object value) {
        return this.and(cond, cri -> cri.and(key).lte(value));
    }

    public CriteriaBuilder lte(String key, Object value) {
        return this.lte(true, key, value);
    }

    public CriteriaBuilder gt(boolean cond, String key, Object value) {
        return this.and(cond, cri -> cri.and(key).gt(value));
    }

    public CriteriaBuilder gt(String key, Object value) {
        return this.gt(true, key, value);
    }

    public CriteriaBuilder gte(boolean cond, String key, Object value) {
        return this.and(cond, cri -> cri.and(key).gte(value));
    }

    public CriteriaBuilder gte(String key, Object value) {
        return this.gte(true, key, value);
    }

    public CriteriaBuilder bte(boolean cond, String key, Object gte, Object lte) {
        return this.and(cond, cri -> cri.and(key).gte(gte).lte(lte));
    }

    public CriteriaBuilder bte(String key, Object gte, Object lte) {
        return this.bte(true, key, gte, lte);
    }

    public CriteriaBuilder bt(boolean cond, String key, Object gte, Object lte) {
        return this.and(cond, cri -> cri.and(key).gt(gte).lt(lte));
    }

    public CriteriaBuilder bt(String key, Object gte, Object lte) {
        return this.bt(true, key, gte, lte);
    }

    public CriteriaBuilder btl(boolean cond, String key, Object gte, Object lte) {
        return this.and(cond, cri -> cri.and(key).gt(gte).lte(lte));
    }

    public CriteriaBuilder btl(String key, Object gte, Object lte) {
        return this.btl(true, key, gte, lte);
    }

    public CriteriaBuilder btAuto(boolean cond, String key, Object gte, Object lte) {
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

    public CriteriaBuilder btAuto(String key, Object gte, Object lte) {
        return this.btAuto(true, key, gte, lte);
    }

    public CriteriaBuilder bteAuto(boolean cond, String key, Object gte, Object lte) {
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

    public CriteriaBuilder bteAuto(String key, Object gte, Object lte) {
        return this.bteAuto(true, key, gte, lte);
    }

    public CriteriaBuilder btlAuto(boolean cond, String key, Object gte, Object lte) {
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

    public CriteriaBuilder btlAuto(String key, Object gte, Object lte) {
        return this.btlAuto(true, key, gte, lte);
    }

    public CriteriaBuilder btgAuto(boolean cond, String key, Object gte, Object lte) {
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

    public CriteriaBuilder btgAuto(String key, Object gte, Object lte) {
        return this.btgAuto(true, key, gte, lte);
    }

    public CriteriaBuilder btg(boolean cond, String key, Object gte, Object lte) {
        return this.and(cond, cri -> cri.and(key).gte(gte).lt(lte));
    }

    public CriteriaBuilder btg(String key, Object gte, Object lte) {
        return this.btg(true, key, gte, lte);
    }

    public CriteriaBuilder elemMatch(boolean cond, String key, Criteria criteria) {
        return this.and(cond, cri -> cri.and(key).elemMatch(criteria));
    }

    public CriteriaBuilder elemMatch(String key, Criteria criteria) {
        return this.elemMatch(true, key, criteria);
    }

    public CriteriaBuilder regex(boolean cond, String key, String regex) {
        return this.and(cond, cri -> cri.and(key).regex(regex));
    }

    public CriteriaBuilder regex(String key, String regex) {
        return this.regex(true, key, regex);
    }

    public CriteriaBuilder regex(boolean cond, String key, Pattern pattern) {
        return this.and(cond, cri -> cri.and(key).regex(pattern));
    }

    public CriteriaBuilder regex(String key, Pattern regex) {
        return this.regex(true, key, regex);
    }

    public CriteriaBuilder regex(boolean cond, String key, String regex, String options) {
        return this.and(cond, cri -> cri.and(key).regex(regex, options));
    }

    public CriteriaBuilder regex(String key, String regex, String options) {
        return this.regex(true, key, regex, options);
    }

    public CriteriaBuilder not(boolean cond, String key) {
        return this.and(cond, cri -> cri.and(key).not());
    }

    public CriteriaBuilder not(String key) {
        return this.not(true, key);
    }

    public CriteriaBuilder in(boolean cond, String key, Collection<?> value) {
        return this.and(cond, cri -> cri.and(key).in(value));
    }

    public CriteriaBuilder in(String key, Collection<?> value) {
        return this.in(true, key, value);
    }

    public CriteriaBuilder nin(boolean cond, String key, Collection<?> value) {
        return this.and(cond, cri -> cri.and(key).nin(value));
    }

    public CriteriaBuilder nin(String key, Collection<?> value) {
        return this.nin(true, key, value);
    }

    public CriteriaBuilder all(boolean cond, String key, Collection<?> value) {
        return this.and(cond, cri -> cri.and(key).all(value));
    }

    public CriteriaBuilder all(String key, Collection<?> value) {
        return this.all(true, key, value);
    }

    public CriteriaBuilder size(boolean cond, String key, int size) {
        return this.and(cond, cri -> cri.and(key).size(size));
    }

    public CriteriaBuilder size(String key, int size) {
        return this.size(true, key, size);
    }

    public CriteriaBuilder exists(boolean cond, String key, boolean exists) {
        return this.and(cond, cri -> cri.and(key).exists(exists));
    }

    public CriteriaBuilder exists(String key, boolean exists) {
        return this.exists(true, key, exists);
    }

    public CriteriaBuilder exists(String key) {
        return this.exists(key, true);
    }

    public CriteriaBuilder type(boolean cond, String key, int type) {
        return this.and(cond, cri -> cri.and(key).type(type));
    }

    public CriteriaBuilder type(String key, int type) {
        return this.type(true, key, type);
    }

    public CriteriaBuilder alike(boolean cond, String key, Example<?> sample) {
        return this.and(cond, cri -> cri.and(key).alike(sample));
    }

    public CriteriaBuilder alike(String key, Example<?> sample) {
        return this.alike(true, key, sample);
    }

    public Query query() {
        return Query.query(this.criteria);
    }
}
