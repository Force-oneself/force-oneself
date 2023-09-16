package com.quan.producer.core.context;

/**
 * @author Force-oneself
 * @description CandidateHolder 整个群推生产过程中的数据源封装对象
 * @date 2021-09-27
 **/
public interface CandidateHolder<T> extends Holder<T> {

    /**
     * 是否会员
     *
     * @return 是否
     */
    default boolean isMember() {
        return false;
    }

    /**
     * 是否抖音
     *
     * @return 是否
     */
    default boolean isDy() {
        return false;
    }

    /**
     * 是否拥有手机号
     *
     * @return 是否
     */
    default boolean hasPhone() {
        return false;
    }

    /**
     * 是否公众号
     *
     * @return 是否
     */
    default boolean isGzh() {
        return false;
    }

    /**
     * 是否企微
     *
     * @return 是否
     */
    default boolean isQw() {
        return false;
    }

    /**
     * 是否拥有unionid
     *
     * @return 是否
     */
    default boolean hasUnionid() {
        return false;
    }

}
