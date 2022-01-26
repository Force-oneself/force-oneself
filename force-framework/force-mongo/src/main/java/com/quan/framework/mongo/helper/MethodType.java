package com.quan.framework.mongo.helper;

/**
 * @author Force-oneself
 * @description MethodMode
 * @date 2022-01-25
 */
public enum MethodType {

    COUNT,
    FIND,
    COUNT_DOCUMENTS,
    ESTIMATED_DOCUMENT_COUNT,
    DISTINCT,
    AGGREGATE,
    WATCH,
    MAP_REDUCE,
    BULK_WRITE,
    INSERT_ONE,
    INSERT_MANY,
    DELETE_ONE,
    DELETE_MANY,
    REPLACE_ONE,
    UPDATE_ONE,
    UPDATE_MANY,
    FIND_ONE_AND_DELETE,
    FIND_ONE_AND_REPLACE,
    FIND_ONE_AND_UPDATE,
    DROP,
    CREATE_INDEX,
    CREATE_INDEXES,
    LIST_INDEXES,
    DROP_INDEX
}
