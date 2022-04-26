package com.quan.framework.elasticsearch.api;

import com.quan.framework.elasticsearch.enums.search.Aggregator;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * AggregateType
 *
 * @author Force-oneself
 * @date 2022-04-23
 */
@Getter
@Setter
@Accessors(chain = true)
public abstract class AggregateType<P, R> {

    private Aggregator aggregator;
    private R field;

    public AggregateType<P, R> field(P param) {
        this.field = parse(param);
        return this;
    }

    protected abstract R parse(P p);
}
