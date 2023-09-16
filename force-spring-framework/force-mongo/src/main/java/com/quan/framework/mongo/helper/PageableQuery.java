package com.quan.framework.mongo.helper;

/**
 * @author Force-oneself
 * @Description PageableQuery
 * @date 2021-08-20
 */
public interface PageableQuery<T> extends QueryDefinition<T> {

    /**
     * 当前页码
     *
     * @return 页码
     */
    default int current() {
        return 1;
    }

    /**
     * 分页实现需要跳过的数据
     *
     * @return 跳过的数
     */
    default long skip() {
        int current = current();
        if (current < 1) {
            current = 1;
        }
        int size = size();
        if (size < 0) {
            size = 10;
        }
        return size * (current - 1L);
    }

    /**
     * 分页的大小
     *
     * @return 分页的数据量
     */
    default int size() {
        return 10;
    }

    /**
     * 是否启用分页, 不启用则是全量
     *
     * @return 是否启用
     */
    default boolean enable() {
        return true;
    }
}
