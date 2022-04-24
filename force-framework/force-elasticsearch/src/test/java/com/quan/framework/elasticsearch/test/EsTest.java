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
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;

/**
 * SearchTest
 *
 * @author Force-oneself
 * @date 2022-04-23
 */
public class EsTest extends ForceElasticsearchApplicationTest {

    public static final String ES_DEMO_INDEX = "es_demo";
    private final RestHighLevelClient restHighLevelClient;

    public EsTest(RestHighLevelClient restHighLevelClient) {
        this.restHighLevelClient = restHighLevelClient;
    }

    @Test
    public void index() throws IOException {
        CreateIndexRequest request = new CreateIndexRequest(ES_DEMO_INDEX);
        request.mapping("", XContentType.JSON);
        CreateIndexResponse response = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
        boolean ack = response.isAcknowledged();
        System.out.println(ack);
    }

    @Test
    public void query() throws IOException {

        // term 关键字
        this.query(QueryBuilders.termQuery("termFiledName", "term"));
        // bool 多条件
        this.query(QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("must", "must"))
                .should(QueryBuilders.matchQuery("should", "shoule"))
                .mustNot(QueryBuilders.matchQuery("must_not", "must_not"))
                .filter(QueryBuilders.matchQuery("filter", "filter")));

        // prefix 前缀
        this.query(QueryBuilders.prefixQuery("prefix", "prefix"));
        // wildcard 通配符
        this.query(QueryBuilders.wildcardQuery("wildcard", "wildcard*"));
        // range 范围
        this.query(QueryBuilders.rangeQuery("range").gt(12).lt(22));
        // ids
        this.query(QueryBuilders.idsQuery().addIds("1", "2"));
        // matchPhrase 短词
        this.query(QueryBuilders.matchPhraseQuery("matchPhrase", "matchPhrase"));
    }

    private void query(QueryBuilder query) throws IOException {
        SearchRequest request = new SearchRequest(ES_DEMO_INDEX);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(query);
        request.source(sourceBuilder);
        SearchResponse search = restHighLevelClient.search(request, RequestOptions.DEFAULT);
        Arrays.stream(search.getHits().getHits())
                .map(SearchHit::getSourceAsString)
                .forEach(System.out::println);
    }
}
