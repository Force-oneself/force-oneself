package com.quan.framework.elasticsearch.api;

import com.quan.framework.elasticsearch.enums.search.Aggregator;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * StringAggregateType
 *
 * @author Force-oneself
 * @date 2022-04-23
 */
@Getter
@Setter
@Accessors(chain = true)
public class StringAggregateType extends AggregateType<String, String>{

    @Override
    protected String parse(String s) { return s; }

    public static StringAggregateType countAll() {
        return (StringAggregateType) new StringAggregateType().setAggregator(Aggregator.COUNT).field(null);
    }

    public static StringAggregateType count(String field) {
        return (StringAggregateType) new StringAggregateType().setAggregator(Aggregator.COUNT).field(field);
    }

    public static StringAggregateType min(String field) {
        return (StringAggregateType) new StringAggregateType().setAggregator(Aggregator.MIN).field(field);
    }

    public static StringAggregateType average(String field) {
        return (StringAggregateType) new StringAggregateType().setAggregator(Aggregator.AVERAGE).field(field);
    }

    public static StringAggregateType max(String field) {
        return (StringAggregateType) new StringAggregateType().setAggregator(Aggregator.MAX).field(field);
    }

    public static StringAggregateType sum(String field) {
        return (StringAggregateType) new StringAggregateType().setAggregator(Aggregator.SUM).field(field);
    }
}
