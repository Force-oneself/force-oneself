package com.quan.producer.core.adapter.iterator;


import com.quan.producer.core.context.CandidateHolder;

/**
 * @author Force-oneself
 * @description CursorIteratorAdapter
 * @date 2021-09-28
 **/
public interface WrapIteratorAdapter<T, R> extends IteratorAdapter<R> {

    /**
     * 封装源数据迭代转换为CandidateHolder
     *
     * @return CandidateHolder
     */
    @Override
    default CandidateHolder<R> next() {
        return wrapTarget(nextTarget());
    }

    /**
     * 下一源数据迭代
     *
     * @return 源数据
     */
    T nextTarget();

    /**
     * 包装返回源数据信息
     *
     * @param next 源数据信息
     * @return CandidateHolder
     */
    CandidateHolder<R> wrapTarget(T next);
}
