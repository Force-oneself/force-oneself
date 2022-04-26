package com.quan.framework.elasticsearch.api;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * PageList
 *
 * @author Force-oneself
 * @date 2022-04-23
 */
@Getter
@Setter
@Accessors(chain = true)
public class PagedList<T> {

    private List<T> list;
    private Page page;

    public static <T> PagedList<T> newInstance() {
        return new PagedList<>(null, null);
    }

    public static <T> PagedList<T> newInstance(List<T> list) {
        return new PagedList<>(list, Page.newInstance(1L, (long) list.size(), (long) list.size()));
    }

    /**
     * 分页返回数据对象
     *
     * @param list 当前页列表数据
     * @param page 当前分页信息
     * @param <T>  列表数据对象类型
     * @return 分页返回数据对象
     */
    public static <T> PagedList<T> newInstance(List<T> list, Page page) { return new PagedList<>(list, page); }

    private PagedList(List<T> list, Page page) {
        this.list = list;
        this.page = page;
    }

    protected PagedList() {}
}
