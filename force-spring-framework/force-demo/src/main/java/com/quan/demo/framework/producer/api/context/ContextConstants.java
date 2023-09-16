package com.quan.demo.framework.producer.api.context;

/**
 * @author Force-oneself
 * @description ContextConstants
 * @date 2021-09-28
 **/
public interface ContextConstants {

    /**
     * 迭代统计总数量
     */
    String ITERATOR_COUNT_TOTAL = "iterator-count-total";

    /**
     * 本地线程上下文保存的key
     */
    String CONTEXT = "$$context";

    /**
     * 群推任务key
     */
    String TASK_ID = "group-push-task-key";
}
