package com.quan.framework.elasticsearch.test;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Arrays;

/**
 * SearchTest
 *
 * @author Force-oneself
 * @date 2022-04-23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@SpringBootApplication
public class EsTest {

    public static final String ES_DEMO_INDEX = "es_demo";
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Test
    public void index() throws IOException {
        CreateIndexRequest request = new CreateIndexRequest(ES_DEMO_INDEX);
        request.mapping("{\n" +
                "    \"properties\": {\n" +
                "      \"name\": {\n" +
                "        \"type\": \"text\"\n" +
                "      },\n" +
                "      \"age\": {\n" +
                "        \"type\": \"integer\"\n" +
                "      },\n" +
                "      \"city\": {\n" +
                "        \"type\": \"text\",\n" +
                "        \"fields\": {\n" +
                "          \"keyword\": {\n" +
                "            \"type\": \"keyword\",\n" +
                "            \"ignore_above\": 256\n" +
                "          }\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  }", XContentType.JSON);
        CreateIndexResponse response = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
        boolean ack = response.isAcknowledged();
        System.out.println(ack);
    }

    @Test
    public void query() throws IOException {

        // term 关键字
        this.query(QueryBuilders.termQuery("firstname", "Parker"));
        // bool 多条件
        this.query(QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("must", "must"))
                .should(QueryBuilders.matchQuery("should", "shoule"))
                .mustNot(QueryBuilders.matchQuery("must_not", "must_not"))
                .filter(QueryBuilders.matchQuery("filter", "filter")));

        // prefix 前缀
        this.query(QueryBuilders.prefixQuery("address", "990"));
        // wildcard 通配符
        this.query(QueryBuilders.wildcardQuery("city", "Lop*"));
        // range 范围
        this.query(QueryBuilders.rangeQuery("balance").gt(9800).lt(10000));
        // ids
        this.query(QueryBuilders.idsQuery().addIds("136", "970"));
        // matchPhrase 短词
        this.query(QueryBuilders.matchPhraseQuery("email", "forbeswallace@pheast.com"));
    }

    private void query(QueryBuilder query) throws IOException {
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(query);
        SearchRequest request = new SearchRequest(new String[]{"bank"}, sourceBuilder);
        SearchResponse search = restHighLevelClient.search(request, RequestOptions.DEFAULT);
        Arrays.stream(search.getHits().getHits())
                .map(SearchHit::getSourceAsString)
                .forEach(System.out::println);
    }
}
