package com.quan.framework.elasticsearch.api;

import java.util.ArrayList;
import java.util.List;

/**
 * Searcher
 *
 * @author Force-oneself
 * @date 2022-04-21
 */
public class Searcher {

    /**
     * 基础查找id
     * 对每次实例查找分配一个查找id 缓存此次查找
     * 当存在基础查找时 本次在基础查找的结果集基础上进行查找
     */
    private String baseSearchId;
    /**
     * 查找字段名称
     */
    private List<String> selected = new ArrayList<>();
    /**
     * 查找目标模型
     */
    private String fromEntity;
    /**
     * 查找条件
     */
//    private SearchCondition<InstanceSearch> searchCondition;
    /**
     * 排序条件
     */
//    private List<OrderCondition<String>> orderBy;


}
