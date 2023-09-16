package com.quan.framework.elasticsearch.api;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Page
 *
 * @author Force-oneself
 * @date 2022-04-23
 */
@Getter
@Setter
@Accessors(chain = true)
public class Page {

    private Long curPage = 0L;
    /**
     * 单页数据项大小
     */
    private Long pageSize = 0L;
    /**
     * 总数据项大小
     */
    private Long totalSize = 0L;

    /**
     * 创建分页信息类对象
     *
     * @param curPage 当前页
     * @return 分页信息
     */
    public static Page newInstance(Long curPage) { return new Page(curPage, 0L, 0L); }

    /**
     * 创建分页信息类对象
     *
     * @param curPage  当前页
     * @param pageSize 单页数据大小
     * @return 分页信息
     */
    public static Page newInstance(Long curPage, Long pageSize) { return new Page(curPage, pageSize, 0L); }

    /**
     * 创建分页信息类对象
     *
     * @param curPage   当前页
     * @param pageSize  单页数据大小
     * @param totalSize 总数据量
     * @return 分页信息
     */
    public static Page newInstance(Long curPage, Long pageSize, Long totalSize) {
        return new Page(curPage, pageSize, totalSize);
    }

    private Page(Long curPage, Long pageSize, Long totalSize) {
        this.curPage = curPage;
        this.pageSize = pageSize;
        this.totalSize = totalSize;
    }

    protected Page() {}
}
