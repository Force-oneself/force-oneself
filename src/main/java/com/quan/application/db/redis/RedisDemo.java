package com.quan.application.db.redis;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description:
 * @Author heyq
 * @Date 2021-04-10
 **/
//@Component
@AllArgsConstructor
public class RedisDemo {

    private RedisTemplate<String, RedisSortSetBean> redisTemplate;


    public void redisForString() {
        Set<ZSetOperations.TypedTuple<RedisSortSetBean>> beans = new HashSet<>();
        for (int i=0; i < 10; i++) {
            RedisSortSetBean redisSortSetBean = new RedisSortSetBean();
            beans.add(new DefaultTypedTuple<>(redisSortSetBean, Double.valueOf(redisSortSetBean.getId())));
        }
        redisTemplate.opsForZSet().add("key", beans);
        redisTemplate.opsForZSet().add("key", new RedisSortSetBean(), new RedisSortSetBean().getAge());
        redisTemplate.opsForZSet().range("key", 0, 10);
        // 自增
        redisTemplate.opsForZSet().incrementScore("key", new RedisSortSetBean(), new RedisSortSetBean().getId());
    }
}

