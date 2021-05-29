package com.quan.framework.demo;

import lombok.AllArgsConstructor;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Component;

/**
 * @Description: class ElasticsearchDemo
 * @Author Force丶Oneself
 * @Date 2021-05-28
 **/
@Component
@AllArgsConstructor
public class ElasticsearchDemo {

    private final RestHighLevelClient client;


}
