package com.quan.framework.elasticsearch.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.*;
import org.springframework.context.annotation.Configuration;

/**
 * EsConfig.java
 *
 * @author Force-oneself
 * @date 2022-04-20 13:53
 */
@Configuration
public class EsConfig {

//    @Bean
    public RestHighLevelClient restHighLevelClient() {
        RestClientBuilder restClient = RestClient.builder(new HttpHost("localhost", 9200, "http"));

        // 定义监听器，节点出现故障会收到通知。
        restClient.setFailureListener(new RestClient.FailureListener() {
            @Override
            public void onFailure(Node node) {
                super.onFailure(node);
            }
        });
        // 定义节点选择器 这个是跳过data=false，ingest为false的节点
        restClient.setNodeSelector(NodeSelector.SKIP_DEDICATED_MASTERS);
        // 定义默认请求配置回调
        restClient.setRequestConfigCallback(requestConfigBuilder -> requestConfigBuilder
                // 连接超时（默认为1秒）
                .setConnectTimeout(90000)
                // 套接字超时（默认为30秒）
                .setSocketTimeout(30000));

        return new RestHighLevelClient(restClient);
    }

}
