package com.quan.framework.elasticsearch.api;


import com.quan.framework.elasticsearch.enums.search.ConditionType;
import com.quan.framework.elasticsearch.enums.search.SearchType;

/**
 * SearchCondition.java
 *
 * @author Force-oneself
 * @date 2022-04-22 23:49
 */
public class SearchCondition<U> extends StringConditionItem<String, U> {

    protected SearchCondition() { super();}

    public SearchCondition(U parent) {
        super(parent, ConditionType.NESTED, SearchType.AND);
    }

}