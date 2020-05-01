package com.quan.search.elasticsearch;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;

import java.io.IOException;

/**
 * @author Force-Oneself
 * @date 2020-05-01
 */
public class ElasticsearchTest {

    @Test
    public void searchRequest() throws IOException {
        RestHighLevelClient highLevelClient = ElasticsearchUtils.getConnection();
        SearchRequest searchRequest = new SearchRequest("sku");
        // 设置查询的类型
        searchRequest.types("doc");

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("name", "手机");

        searchSourceBuilder.query(matchQueryBuilder);
        searchRequest.source(searchSourceBuilder);


        // 获取查询结果
        SearchResponse searchResponse = highLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();
        long totalHits = hits.getTotalHits();
        System.out.println("记录数" + totalHits);

        SearchHit[] searchHits = hits.getHits();
        for (SearchHit hit: searchHits){
            String sourceAsString = hit.getSourceAsString();
            System.out.println(sourceAsString);
        }

        ElasticsearchUtils.close(highLevelClient);
    }

    @Test
    public void searchRequestByBool() throws IOException {
        RestHighLevelClient highLevelClient = ElasticsearchUtils.getConnection();
        SearchRequest searchRequest = new SearchRequest("sku");
        // 设置查询的类型
        searchRequest.types("doc");

        // 布尔查询构建器
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("name", "手机");
        boolQueryBuilder.must(matchQueryBuilder);

        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("brandName", "小米");
        boolQueryBuilder.must(termQueryBuilder);

        searchSourceBuilder.query(boolQueryBuilder);
        searchRequest.source(searchSourceBuilder);

        // 获取查询结果
        SearchResponse searchResponse = highLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();
        long totalHits = hits.getTotalHits();
        System.out.println("记录数" + totalHits);

        SearchHit[] searchHits = hits.getHits();
        for (SearchHit hit: searchHits){
            String sourceAsString = hit.getSourceAsString();
            System.out.println(sourceAsString);
        }

        ElasticsearchUtils.close(highLevelClient);
    }
}
