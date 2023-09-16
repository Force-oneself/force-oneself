package com.quan.framework.elasticsearch.api;


import com.quan.framework.elasticsearch.enums.search.ConditionType;
import com.quan.framework.elasticsearch.enums.search.SearchType;

/**
 * StringConditionItem.java
 *
 * @author Force-oneself
 * @date 2022-04-22 23:49
 */
public class StringConditionItem<T, U> extends ConditionItem<T, U, String, String> {

    protected StringConditionItem() { super();}

    public StringConditionItem(U parent, ConditionType conditionType, SearchType searchType) {
        super(parent, conditionType, searchType);
    }

    @Override
    protected String parse(String s) { return s; }

    @Override
    protected ConditionItem<T, ConditionItem<T, U, String, String>, String, String> createItem(
            ConditionType conditionType, SearchType searchType) {
        return new StringConditionItem<>(this, conditionType, searchType);
    }

}