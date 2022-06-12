package com.quan.demo.algorithm.sort;

import java.util.Arrays;
import java.util.List;

/**
 * Sortable
 *
 * @author Force-oneself
 * @date 2022-06-12
 */
public interface Sortable {

    /**
     * 排序
     *
     * @param unsorted 需要排序的数组
     * @return  /
     */
    <T extends Comparable<T>> T[] sort(T[] unsorted);


    /**
     * 集合排序
     *
     * @param unsorted 需要排序的集合
     * @return  排序集合
     */
    @SuppressWarnings("unchecked")
    default <T extends Comparable<T>> List<T> sort(List<T> unsorted) {
        return Arrays.asList(sort(unsorted.toArray((T[]) new Comparable[unsorted.size()])));
    }
}
