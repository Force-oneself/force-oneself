package com.quan.producer.core.adapter.iterator;

import java.io.Serializable;

/**
 * @author Force-oneself
 * @description PageQuery
 * @date 2021-10-20
 **/
public class PageQuery implements Serializable {

    private static final long serialVersionUID = 2854376375696738425L;

    /**
     * 当前页
     */
    private Long current;

    /**
     * 页大小
     */
    private Long size;

    public PageQuery(long current, long size) {
        this.current = current;
        this.size = size;
    }

    public static PageQuery of(long current, long size) {
        return new PageQuery(current, size);
    }

    public static PageQuery nextPage(PageQuery query) {
        if (query == null) {
            return of(1, 3000);
        }
        query.current++;
        return query;
    }

    public long count() {
        return current * size;
    }

    public Long getCurrent() {
        return current;
    }

    public void setCurrent(Long current) {
        this.current = current;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }
}
