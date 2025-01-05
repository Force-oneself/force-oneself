package com.quan.cache.core;

import org.springframework.beans.factory.DisposableBean;

/**
 * 缓存服务接口
 *
 * @author Force-oneself
 * @date 2025-01-04
 */
public interface CacheService extends DisposableBean {
    /**
     * 获取缓存
     *
     * @param key 缓存键
     * @param <T> 缓存值类型
     * @return 缓存值，如果缓存不存在或已过期则返回 null
     * 
     * 示例：
     * <pre>
     * CacheKey key = new ExpirableKey("myKey", 3600); // 1小时后过期
     * CacheValue<String> value = cacheService.get(key);
     * if (value != null) {
     *     System.out.println("缓存值: " + value.getValue());
     * } else {
     *     System.out.println("缓存已过期或不存在");
     * }
     * </pre>
     */
    <T> CacheValue<T> get(CacheKey key);

    /**
     * 设置缓存
     *
     * @param key   缓存键
     * @param value 缓存值
     * @param <T>  缓存值类型
     * 
     * 示例：
     * <pre>
     * CacheKey key = new ExpirableKey("myKey", 3600); // 1小时后过期
     * cacheService.put(key, new CacheValue<>("myValue"));
     * </pre>
     */
    <T> void put(CacheKey key, CacheValue<T> value);

    /**
     * 删除缓存
     *
     * @param key 缓存键
     * @param <T> 缓存值类型
     * 
     * 示例：
     * <pre>
     * CacheKey key = new ExpirableKey("myKey", 3600);
     * cacheService.remove(key); // 删除指定的缓存
     * </pre>
     */
    void remove(CacheKey key);

    /**
     * 清空缓存
     * 
     * 示例：
     * <pre>
     * cacheService.clear(); // 清空所有缓存
     * </pre>
     */
    void clear();

    /**
     * 检查缓存是否过期
     *
     * @param key 缓存键
     * @return true 表示缓存已过期，false 表示缓存未过期
     * 
     * 示例：
     * <pre>
     * CacheKey key = new ExpirableKey("myKey", 3600);
     * boolean expired = cacheService.isExpired(key);
     * System.out.println("缓存是否过期: " + expired);
     * </pre>
     */
    boolean isExpired(CacheKey key);

    /**
     * 设置缓存的过期时间
     *
     * @param key 缓存键
     * 
     * 示例：
     * <pre>
     * CacheKey key = new ExpirableKey("myKey", 3600);
     * cacheService.setExpiration(key); // 设置过期时间
     * </pre>
     */
    void setExpiration(CacheKey key);

    /**
     * 获取缓存的过期时间
     *
     * @param key 缓存键
     * @return 过期时间，0表示永不过期
     * 
     * 示例：
     * <pre>
     * CacheKey key = new ExpirableKey("myKey", 3600);
     * long remainingTime = cacheService.getExpiration(key);
     * System.out.println("剩余过期时间: " + remainingTime + " 毫秒");
     * </pre>
     */
    long getExpiration(CacheKey key);

    /**
     * 检查缓存服务是否健康
     *
     * @return true 表示缓存服务健康，false 表示不健康
     * 
     * 示例：
     * <pre>
     * if (cacheService.isHealthy()) {
     *     System.out.println("缓存服务健康");
     * } else {
     *     System.out.println("缓存服务不健康");
     * }
     * </pre>
     */
    boolean isHealthy();
}
