package com.quan.demo.redisson;

import com.quan.common.data.structures.graph.AdjacencyList;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description RedissonGraph
 * @Author Force-oneself
 * @Date 2021-06-13
 **/
@Component
public class RedissonGraph extends AdjacencyList<String> {

    @Autowired
    private RedissonClient redissonClient;



}
