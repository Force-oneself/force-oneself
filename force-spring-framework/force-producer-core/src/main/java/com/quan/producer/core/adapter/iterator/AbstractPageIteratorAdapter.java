package com.quan.producer.core.adapter.iterator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

/**
 * @author Force-oneself
 * @description AbstractPageIteratorAdapter
 * @date 2021-10-20
 **/
public abstract class AbstractPageIteratorAdapter<T, R> implements PageIteratorAdapter<T, R> {

    /**
     * 迭代器
     */
    protected Iterator<T> iterator = Collections.emptyIterator();

    /**
     * 分页查询
     */
    protected PageQuery currentPage;

    /**
     * 连续异常的次数，防止无限异常造成死循环
     */
    protected int errorNum = 1;

    /**
     * 出现连续异常的最大次数
     */
    protected int maxErrorLimit = 10;

    /**
     * 异常出现需要跳过时，分页数据标识
     */
    protected final Collection<T> errorMarkPage = new ArrayList<>();

    @Override
    public boolean hasPage() {
        // 分页数据
        Collection<T> page = this.page(this.nextPage(currentPage));
        if (page == null) {
            return false;
        }
        // 异常的处理
        if (page == errorMarkPage && errorNum < maxErrorLimit) {
            errorNum++;
            return hasPage();
        } else if (page == errorMarkPage) {
            return false;
        }
        errorNum = 1;
        // 允许子类返回负数来跳过该分页，来解决统计出现的异常
        long count = this.count();
        // 判断是否遍历完全部
        boolean over = page.isEmpty() && (count < 0 || currentPage.count() < count);
        if (over) {
            return hasPage();
        }
        this.iterator = page.iterator();
        return this.iterator.hasNext();
    }

    @Override
    public Iterator<T> iterator() {
        return iterator;
    }

    /**
     * 返回下一个分页条件
     *
     * @param pageQuery 分页条件
     * @return 下一个
     */
    protected PageQuery nextPage(PageQuery pageQuery) {
        this.currentPage = PageQuery.nextPage(pageQuery);
        return this.currentPage;
    }

    /**
     * 分页查询
     *
     * @param pageQuery 分页条件
     * @return 分页数据
     */
    protected abstract Collection<T> page(PageQuery pageQuery);

}
