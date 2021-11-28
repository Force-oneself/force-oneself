package com.quan.demo.page;

/**
 * @author Force-oneself
 * @Description PageQuery 分页查询接口的顶级抽象
 * @date 2021-11-22
 */
public interface PageQuery<T> {

    /**
     * 页大小
     *
     * @return 每页的大小
     */
    int size();

    /**
     * 开始分页的起始点
     *
     * @return 起始位置
     */
    T point();

}
