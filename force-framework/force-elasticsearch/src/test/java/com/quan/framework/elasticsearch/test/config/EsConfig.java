package com.quan.framework.elasticsearch.test.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.lang.NonNull;

/**
 * EsConfig
 *
 * @author Force-oneself
 * @date 2022-04-23
 */
//@Configuration
public class EsConfig extends AbstractElasticsearchConfiguration {

    @Override
    @Bean
    @NonNull
    public RestHighLevelClient elasticsearchClient() {
        return RestClients
                .create(ClientConfiguration.builder()
                        .connectedTo("localhost:9200")
                        .build())
                .rest();
    }
}
