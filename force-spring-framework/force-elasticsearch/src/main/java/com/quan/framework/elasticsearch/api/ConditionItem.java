package com.quan.framework.elasticsearch.api;

import com.quan.framework.elasticsearch.enums.search.ConditionType;
import com.quan.framework.elasticsearch.enums.search.MatchType;
import com.quan.framework.elasticsearch.enums.search.SearchType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * ConditionItem.java
 *
 * @author Force-oneself
 * @date 2022-04-22 23:48
 */
@Getter
@Setter
@Accessors(chain = true)
public abstract class ConditionItem<T, O, P, R> {

    protected transient O parent;
    private transient Function<P, R> parseFunction = this::parse;

    private ConditionType conditionType;
    private SearchType searchType;
    private MatchType matchType;
    private R param;
    private Object value;
    private List<ConditionItem<T, ?, P, R>> conditions = new ArrayList<>();

    public ConditionItem() {this(null, ConditionType.SMOOTH, SearchType.AND);}

    public ConditionItem(O parent, ConditionType conditionType, SearchType searchType) {
        this.parent = parent;
        this.conditionType = conditionType;
        this.searchType = searchType;
    }

    protected abstract R parse(P p);

    protected abstract ConditionItem<T, ConditionItem<T, O, P, R>, P, R> createItem(ConditionType conditionType, SearchType searchType);

    public O out() { return parent; }

    public ConditionItem<T, O, P, R> equal(P param, Object value) {
        return and().equal(param, value);
    }

    public ConditionItem<T, O, P, R> equal(boolean condition, P param, Object value) {
        if (!condition) { return this; }
        return and().equal(param, value);
    }

    public ConditionItem<T, O, P, R> notEqual(P param, Object value) {
        return and().notEqual(param, value);
    }

    public ConditionItem<T, O, P, R> notEqual(boolean condition, P param, Object value) {
        if (!condition) { return this; }
        return and().notEqual(param, value);
    }

    public ConditionItem<T, O, P, R> greaterThan(P param, Object value) {
        return and().greaterThan(param, value);
    }

    public ConditionItem<T, O, P, R> greaterThan(boolean condition, P param, Object value) {
        if (!condition) { return this; }
        return and().greaterThan(param, value);
    }

    public ConditionItem<T, O, P, R> greaterEqual(P param, Object value) {
        return and().greaterEqual(param, value);
    }

    public ConditionItem<T, O, P, R> greaterEqual(boolean condition, P param, Object value) {
        if (!condition) { return this; }
        return and().greaterEqual(param, value);
    }

    public ConditionItem<T, O, P, R> lessThan(P param, Object value) {
        return and().lessThan(param, value);
    }

    public ConditionItem<T, O, P, R> lessThan(boolean condition, P param, Object value) {
        if (!condition) { return this; }
        return and().lessThan(param, value);
    }

    public ConditionItem<T, O, P, R> lessEqual(P param, Object value) {
        return and().lessEqual(param, value);
    }

    public ConditionItem<T, O, P, R> lessEqual(boolean condition, P param, Object value) {
        if (!condition) { return this; }
        return and().lessEqual(param, value);
    }

    public ConditionItem<T, O, P, R> like(P param, Object value) {
        return and().like(param, value);
    }

    public ConditionItem<T, O, P, R> like(boolean condition, P param, Object value) {
        if (!condition) { return this; }
        return and().like(param, value);
    }

    public ConditionItem<T, O, P, R> notLike(P param, Object value) {
        return and().notLike(param, value);
    }

    public ConditionItem<T, O, P, R> notLike(boolean condition, P param, Object value) {
        if (!condition) { return this; }
        return and().notLike(param, value);
    }

    public ConditionItem<T, O, P, R> likeLeft(P param, Object value) {
        return and().likeLeft(param, value);
    }

    public ConditionItem<T, O, P, R> likeLeft(boolean condition, P param, Object value) {
        if (!condition) { return this; }
        return and().likeLeft(param, value);
    }

    public ConditionItem<T, O, P, R> likeRight(P param, Object value) {
        return and().likeRight(param, value);
    }

    public ConditionItem<T, O, P, R> likeRight(boolean condition, P param, Object value) {
        if (!condition) { return this; }
        return and().likeRight(param, value);
    }

    public ConditionItem<T, O, P, R> in(P param, List<?> value) {
        return and().in(param, value);
    }

    public ConditionItem<T, O, P, R> in(boolean condition, P param, List<?> value) {
        if (!condition) { return this; }
        return and().in(param, value);
    }

    public ConditionItem<T, O, P, R> notIn(P param, List<?> value) {
        return and().notIn(param, value);
    }

    public ConditionItem<T, O, P, R> notIn(boolean condition, P param, List<?> value) {
        if (!condition) { return this; }
        return and().notIn(param, value);
    }

    /**
     * 包含边界
     */
    public ConditionItem<T, O, P, R> between(P param, Object valueBegin, Object valueTo) {
        return and().between(param, valueBegin, valueTo);
    }

    public ConditionItem<T, O, P, R> between(boolean condition, P param, Object valueBegin, Object valueTo) {
        if (!condition) { return this; }
        return and().between(param, valueBegin, valueTo);
    }

    /**
     * 不包含边界
     */
    public ConditionItem<T, O, P, R> notBetween(P param, Object valueBegin, Object valueTo) {
        return and().notBetween(param, valueBegin, valueTo);
    }

    public ConditionItem<T, O, P, R> notBetween(boolean condition, P param, Object valueBegin, Object valueTo) {
        if (!condition) { return this; }
        return and().notBetween(param, valueBegin, valueTo);
    }

    public ConditionItem<T, O, P, R> isNull(P param) {
        return and().isNull(param);
    }

    public ConditionItem<T, O, P, R> isNull(boolean condition, P param) {
        if (!condition) { return this; }
        return and().isNull(param);
    }

    public ConditionItem<T, O, P, R> notNull(P param) {
        return and().notNull(param);
    }

    public ConditionItem<T, O, P, R> notNull(boolean condition, P param) {
        if (!condition) { return this; }
        return and().notNull(param);
    }

    /**
     * and ConditionItem
     */
    public ConditionWrapper<T, O, P, R> and() {
        ConditionItem<T, ConditionItem<T, O, P, R>, P, R> conditionItem = createItem(ConditionType.SMOOTH, SearchType.AND);
        conditions.add(conditionItem);
        return new ConditionWrapper<>(conditionItem);
    }

    /**
     * or ConditionItem
     */
    public ConditionWrapper<T, O, P, R> or() {
        ConditionItem<T, ConditionItem<T, O, P, R>, P, R> conditionItem = createItem(ConditionType.SMOOTH, SearchType.OR);
        conditions.add(conditionItem);
        return new ConditionWrapper<>(conditionItem);
    }

    /**
     * and ( ConditionItem )
     */
    public ConditionItem<T, ConditionItem<T, O, P, R>, P, R> nestedAnd() {
        ConditionItem<T, ConditionItem<T, O, P, R>, P, R> conditionItem = createItem(ConditionType.NESTED, SearchType.AND);
        conditions.add(conditionItem);
        return conditionItem;
    }

    /**
     * or ( ConditionItem )
     */
    public ConditionItem<T, ConditionItem<T, O, P, R>, P, R> nestedOr() {
        ConditionItem<T, ConditionItem<T, O, P, R>, P, R> conditionItem = createItem(ConditionType.NESTED, SearchType.OR);
        conditions.add(conditionItem);
        return conditionItem;
    }

    public static class ConditionWrapper<K, U, P, R> {

        private final ConditionItem<K, ConditionItem<K, U, P, R>, P, R> conditionItem;

        public ConditionWrapper(ConditionItem<K, ConditionItem<K, U, P, R>, P, R> conditionItem) {
            this.conditionItem = conditionItem;
        }

        public void setConditionContent(MatchType matchType, P param, Object value) {
            conditionItem.matchType = matchType;
            conditionItem.param = conditionItem.parseFunction.apply(param);
            conditionItem.value = value;
        }

        public ConditionItem<K, U, P, R> equal(P param, Object value) {
            setConditionContent(MatchType.EQUAL, param, value);
            return conditionItem.out();
        }

        public ConditionItem<K, U, P, R> notEqual(P param, Object value) {
            setConditionContent(MatchType.NOT_EQUAL, param, value);
            return conditionItem.out();
        }

        public ConditionItem<K, U, P, R> greaterThan(P param, Object value) {
            setConditionContent(MatchType.GREATER_THAN, param, value);
            return conditionItem.out();
        }

        public ConditionItem<K, U, P, R> greaterEqual(P param, Object value) {
            setConditionContent(MatchType.GREATER_EQUAL, param, value);
            return conditionItem.out();
        }

        public ConditionItem<K, U, P, R> lessThan(P param, Object value) {
            setConditionContent(MatchType.LESS_THAN, param, value);
            return conditionItem.out();
        }

        public ConditionItem<K, U, P, R> lessEqual(P param, Object value) {
            setConditionContent(MatchType.LESS_EQUAL, param, value);
            return conditionItem.out();
        }

        public ConditionItem<K, U, P, R> like(P param, Object value) {
            setConditionContent(MatchType.LIKE, param, value);
            return conditionItem.out();
        }

        public ConditionItem<K, U, P, R> notLike(P param, Object value) {
            setConditionContent(MatchType.NOT_LIKE, param, value);
            return conditionItem.out();
        }

        public ConditionItem<K, U, P, R> likeLeft(P param, Object value) {
            setConditionContent(MatchType.LIKE_LEFT, param, value);
            return conditionItem.out();
        }

        public ConditionItem<K, U, P, R> likeRight(P param, Object value) {
            setConditionContent(MatchType.LIKE_RIGHT, param, value);
            return conditionItem.out();
        }

        public ConditionItem<K, U, P, R> in(P param, List<?> value) {
            setConditionContent(MatchType.IN, param, value);
            return conditionItem.out();
        }

        public ConditionItem<K, U, P, R> notIn(P param, List<?> value) {
            setConditionContent(MatchType.NOT_IN, param, value);
            return conditionItem.out();
        }

        public ConditionItem<K, U, P, R> between(P param, Object valueBegin, Object valueTo) {
            setConditionContent(MatchType.BETWEEN, param, Pair.of(valueBegin, valueTo));
            return conditionItem.out();
        }

        public ConditionItem<K, U, P, R> notBetween(P param, Object valueBegin, Object valueTo) {
            setConditionContent(MatchType.NOT_BETWEEN, param, Pair.of(valueBegin, valueTo));
            return conditionItem.out();
        }

        public ConditionItem<K, U, P, R> isNull(P param) {
            setConditionContent(MatchType.IS_NULL, param, null);
            return conditionItem.out();
        }

        public ConditionItem<K, U, P, R> notNull(P param) {
            setConditionContent(MatchType.NOT_NULL, param, null);
            return conditionItem.out();
        }

    }

}
