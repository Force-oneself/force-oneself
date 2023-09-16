package com.quan.framework.elasticsearch.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;

import java.time.Duration;

/**
 * EsConfig.java
 *
 * @author Force-oneself
 * @date 2022-04-20 13:53
 */
@Configuration
public class EsConfig extends AbstractElasticsearchConfiguration {

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


    @Bean
    @NonNull
    @Override
    public RestHighLevelClient elasticsearchClient() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("some-header", "on every request");

        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("localhost:9200", "localhost:9291")
                .usingSsl()
                .withProxy("localhost:8888")
                .withPathPrefix("ela")
                .withConnectTimeout(Duration.ofSeconds(5))
                .withSocketTimeout(Duration.ofSeconds(3))
                .withDefaultHeaders(httpHeaders)
                .withBasicAuth("username", "password")
                // .withHeaders(() -> {
                //     // 7版本访问8的兼容性设置
                //     HttpHeaders defaultCompatibilityHeaders = new HttpHeaders();
                //     defaultCompatibilityHeaders.add("Accept",
                //             "application/vnd.elasticsearch+json;compatible-with=7");
                //     defaultCompatibilityHeaders.add("Content-Type",
                //             "application/vnd.elasticsearch+json;compatible-with=7");
                //     return defaultCompatibilityHeaders;
                // })
                .build();

        return RestClients.create(clientConfiguration).rest();
    }
}
