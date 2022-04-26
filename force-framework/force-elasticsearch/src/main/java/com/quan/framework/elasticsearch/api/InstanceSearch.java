package com.quan.framework.elasticsearch.api;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * InstanceSearch.java
 *
 * @author Force-oneself
 * @date 2022-04-22 23:48
 */
@Getter
@Setter
@Accessors(chain = true)
public final class InstanceSearch implements Serializable {

    /**
     * 基础查找id
     * 对每次实例查找分配一个查找id 缓存此次查找
     * 当存在基础查找时 本次在基础查找的结果集基础上进行查找
     */
    private String baseSearchId;
    /**
     * 查找字段名称
     */
    private List<String> selected;
    /**
     * 查找目标模型
     */
    private String fromEntity;
    /**
     * 查找条件
     */
    private SearchCondition<InstanceSearch> searchCondition;
    /**
     * 排序条件
     */
    private List<OrderCondition<String>> orderBy;

    private Page page;

    public InstanceSearch select(String... params) {
        if (Objects.isNull(params)) {
            throw new IllegalArgumentException("查询字段列表为空");
        }
        this.selected = Arrays.stream(params).collect(Collectors.toList());
        return this;
    }

    public InstanceSearch from(String fromEntity) {
        if (Objects.isNull(fromEntity)) {
            throw new IllegalArgumentException("查询实体为空");
        }
        this.fromEntity = fromEntity;
        return this;
    }

    public InstanceSearch where(Consumer<SearchCondition<InstanceSearch>> supplier) {
        if (Objects.isNull(supplier)) {
            throw new IllegalArgumentException("查询条件为空");
        }
        this.searchCondition = new SearchCondition<>(this);
        supplier.accept(searchCondition);
        return this;
    }

    public final InstanceSearch orderBy(boolean orderByAsc, String orderField) {
        if (Objects.isNull(orderField)) {
            throw new IllegalArgumentException("排序字段信息为空");
        }
        this.orderBy.add(OrderCondition.order(orderByAsc, orderField));
        return this;
    }

    public final InstanceSearch orderByAsc(String orderField) {
        return orderBy(true, orderField);
    }

    public final InstanceSearch orderByDesc(String orderField) {
        return orderBy(false, orderField);
    }

    public InstanceSearch page(long curPage, long pageSize) {
        if (Objects.nonNull(this.getPage())) {
            throw new IllegalArgumentException("分页信息已设置");
        }
        this.setPage(Page.newInstance(curPage, pageSize));
        return this;
    }
}
