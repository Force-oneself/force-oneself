package com.quan.search.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Force-Oneself
 * @date 2020-05-01
 */
public class ElasticsearchUtils {

    private static String hostName = "127.0.0.1";
    private static int port = 9200;
    private static String scheme = "http";

    /**
     * 获取高级客户端连接
     * @return
     */
    public static RestHighLevelClient getConnection(){
        HttpHost httpHost = new HttpHost(hostName, port, scheme);
        RestClientBuilder restClientBuilder = RestClient.builder(httpHost);
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(restClientBuilder);
        return restHighLevelClient;
    }

    /**
     * 关闭elasticsearch 高级客户端的连接
     * @param restHighLevelClient
     */
    public static void close(RestHighLevelClient restHighLevelClient){
        if (restHighLevelClient != null){
            try {
                restHighLevelClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 单数据插入
     * @param restHighLevelClient
     * @param indexRequest
     * @param map
     * @return
     */
    public static IndexResponse index(RestHighLevelClient restHighLevelClient, IndexRequest indexRequest, Map map){
        indexRequest.source(map);
        // 获取执行结果
        IndexResponse indexResponse = null;
        try {
            indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return indexResponse;
    }

    /**
     * 批量插入数据
     * @param restHighLevelClient
     * @param indexRequest
     * @param map
     * @return
     */
    public static BulkResponse bulk(RestHighLevelClient restHighLevelClient, IndexRequest indexRequest, Map map){
        indexRequest.source(map);
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.add(indexRequest);
        BulkResponse bulkResponse = null;
        try {
            bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bulkResponse;
    }

    public static void main(String[] args) {
        IndexRequest indexRequest = new IndexRequest("sku", "doc","3");
        Map map = new HashMap();
        // 对map进行赋值
        map.put("name", "小米手机");
        map.put("price", 343200);
        indexRequest.source(map);

        // 获取执行结果
        RestHighLevelClient restHighLevelClient = ElasticsearchUtils.getConnection();
        IndexResponse indexResponse = null;
        try {
            indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int status = 0;
        if (indexResponse != null){
            status = indexResponse.status().getStatus();
        }
        System.out.println(status);
        ElasticsearchUtils.close(restHighLevelClient);
    }
}
