package com.quan.framework.config;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Node;
import org.elasticsearch.client.NodeSelector;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @Description: class EsConifg
 * @Author Force丶Oneself
 * @Date 2021-05-28
 **/
@Configuration
public class EsConfig {

    @Bean
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
        restClient.setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
            @Override
            public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder requestConfigBuilder) {
                return requestConfigBuilder
                        // 连接超时（默认为1秒）
                        .setConnectTimeout(90000)
                        // 套接字超时（默认为30秒）
                        .setSocketTimeout(30000);
            }
        });

        RestHighLevelClient client = new RestHighLevelClient(restClient);
        return client;
    }


    public static void testMultiMatchSearch(RestHighLevelClient client) throws IOException {
        // 基础设置
        SearchRequest searchRequest = new SearchRequest("caselist");
        // 搜索源构建对象
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        BoolQueryBuilder queryBuilder =
        //一定要转成小写 toLowerCase() 否则搜索不到，加上以后大小写都可搜索到
                QueryBuilders.boolQuery().should(QueryBuilders.wildcardQuery("case_number", ("*201910*").toLowerCase()));
        // 注意 searchSourceBuilder 默认返回10 条数据，如果需要返回实级条数需要设置
        // 可以使用分页查找 比如设置 searchSourceBuilder.from(1);就是从第二页进行查找，类似于MySQL中的limit


        searchSourceBuilder.from(0);
        searchSourceBuilder.size(10);
        searchSourceBuilder.query(queryBuilder);

        searchRequest.source(searchSourceBuilder);
        // 发起请求，获取结果
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        SearchHits hits = searchResponse.getHits();
        // 得到匹配度高的文档
        SearchHit[] searchHits = hits.getHits();
        // 打印结果集
        System.out.println(searchHits.length);
        for (SearchHit searchHit : searchHits) {
            System.out.println(searchHit.toString());
            System.out.println("=============1");
        }
    }

}
