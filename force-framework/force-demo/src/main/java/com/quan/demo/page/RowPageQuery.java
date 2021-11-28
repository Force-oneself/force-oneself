package com.quan.demo.page;

/**
 * @author Force-oneself
 * @Description RowPageQuery 以正常行数进行分页的查询
 * @date 2021-11-22
 */
public interface RowPageQuery extends PageQuery<Long> {

    @Override
    default Long point() {
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

    default int current() {
        return 1;
    }
}
