package com.quan.framework.elasticsearch.demo;

import com.quan.framework.elasticsearch.util.EsUtils;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.*;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.action.support.replication.ReplicationResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.Scroll;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.SuggestBuilders;
import org.elasticsearch.search.suggest.SuggestionBuilder;
import org.elasticsearch.search.suggest.term.TermSuggestionBuilder;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

/**
 * EsDemo.java
 *
 * @author Force-oneself
 * @date 2022-04-20 14:22
 */
public class EsHighLevelDemo {

    public void index() throws IOException {
        // 字符串JSON
        IndexRequest request = new IndexRequest("posts");
        request.id("1");
        String jsonString = "{" +
                "\"user\":\"kimchy\"," +
                "\"postDate\":\"2013-01-30\"," +
                "\"message\":\"trying out Elasticsearch\"" +
                "}";
        request.source(jsonString, XContentType.JSON);

        // Map
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("user", "kimchy");
        jsonMap.put("postDate", new Date());
        jsonMap.put("message", "trying out Elasticsearch");
        new IndexRequest("posts").id("1").source(jsonMap);

        // Builder
        try {
            XContentBuilder builder = XContentFactory.jsonBuilder();
            builder.startObject();
            builder.field("user", "kimchy");
            builder.timeField("postDate", new Date());
            builder.field("message", "trying out Elasticsearch");
            builder.endObject();
            new IndexRequest("posts").id("1").source(builder);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Key-Value
        new IndexRequest("posts")
                .id("1")
                .source("user", "kimchy",
                        "postDate", new Date(),
                        "message", "trying out Elasticsearch");

        // 路由
        request.routing("routing");

        // 超时时间
        request.timeout(TimeValue.timeValueSeconds(1));
        request.timeout("1s");

        request.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);
        request.setRefreshPolicy("wait_for");

        request.version(2);

        request.opType(DocWriteRequest.OpType.CREATE);
        request.opType("create");

        // 同步查询
        IndexResponse indexResponse = EsUtils.getClient().index(request, RequestOptions.DEFAULT);

        // 异步带监听器回调
        EsUtils.getClient().indexAsync(request, RequestOptions.DEFAULT, new ActionListener<IndexResponse>() {
            @Override
            public void onResponse(IndexResponse indexResponse) {

            }

            @Override
            public void onFailure(Exception e) {

            }
        });

        String index = indexResponse.getIndex();
        String id = indexResponse.getId();
        // 第一次创建文档的情况
        if (indexResponse.getResult() == DocWriteResponse.Result.CREATED) {

            // 文档被重写的情况，因为它已经存在
        } else if (indexResponse.getResult() == DocWriteResponse.Result.UPDATED) {

        }
        ReplicationResponse.ShardInfo shardInfo = indexResponse.getShardInfo();
        // 分片数小于总分片数的情况
        if (shardInfo.getTotal() != shardInfo.getSuccessful()) {

        }
        // 潜在的故障
        if (shardInfo.getFailed() > 0) {
            for (ReplicationResponse.ShardInfo.Failure failure : shardInfo.getFailures()) {
                String reason = failure.reason();
            }
        }

        try {
            IndexResponse response = EsUtils.getClient().index(new IndexRequest("posts")
                    .id("1")
                    .source("field", "value")
                    .setIfSeqNo(10L)
                    .setIfPrimaryTerm(20), RequestOptions.DEFAULT);
        } catch (ElasticsearchException e) {
            if (e.status() == RestStatus.CONFLICT) {
                // 引发的异常表示返回了版本冲突错误
            }
        }
    }

    public void search() throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);

        // 带索引
        new SearchRequest("posts");
        // 带路由
        searchRequest.routing("routing");

        searchRequest.indicesOptions(IndicesOptions.lenientExpandOpen());

        searchRequest.preference("_local");

        // SearchSourceBuilder
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder()
                .query(QueryBuilders.termQuery("user", "kimchy"))
                .from(0)
                .size(5)
                .timeout(new TimeValue(60, TimeUnit.SECONDS));
        searchRequest.indices("posts");
        searchRequest.source(sourceBuilder);

        SearchResponse search = EsUtils.getClient()
                .search(searchRequest, RequestOptions.DEFAULT);

        MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("user", "kimchy")
                .fuzziness(Fuzziness.AUTO)
                .prefixLength(3)
                .maxExpansions(10);

        QueryBuilder matchQueryBuilder1 = matchQuery("user", "kimchy")
                .fuzziness(Fuzziness.AUTO)
                .prefixLength(3)
                .maxExpansions(10);

        searchSourceBuilder.query(matchQueryBuilder);

        // 默认根据_source排序
        sourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.DESC));
        // 根据_id
        sourceBuilder.sort(new FieldSortBuilder("_id").order(SortOrder.ASC));

        // 选择展示字段
        String[] includeFields = new String[]{"title", "innerObject.*"};
        String[] excludeFields = new String[]{"user"};
        sourceBuilder.fetchSource(includeFields, excludeFields);

        // 设置高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        HighlightBuilder.Field highlightTitle = new HighlightBuilder.Field("title");
        highlightTitle.highlighterType("unified");
        highlightBuilder.field(highlightTitle);
        HighlightBuilder.Field highlightUser = new HighlightBuilder.Field("user");
        highlightBuilder.field(highlightUser);
        searchSourceBuilder.highlighter(highlightBuilder);

        // 聚合
        TermsAggregationBuilder aggregation = AggregationBuilders.terms("by_company")
                .field("company.keyword");
        aggregation.subAggregation(AggregationBuilders.avg("average_age")
                .field("age"));
        searchSourceBuilder.aggregation(aggregation);

        SuggestionBuilder<TermSuggestionBuilder> termSuggestionBuilder =
                SuggestBuilders.termSuggestion("user").text("kmichy");
        SuggestBuilder suggestBuilder = new SuggestBuilder();
        suggestBuilder.addSuggestion("suggest_user", termSuggestionBuilder);
        searchSourceBuilder.suggest(suggestBuilder);
    }

    public void scroll() throws IOException {
        SearchRequest searchRequest = new SearchRequest("posts");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(matchQuery("title", "Elasticsearch"));
        searchSourceBuilder.size(100);
        searchRequest.source(searchSourceBuilder);
        searchRequest.scroll(TimeValue.timeValueMinutes(1L));
        SearchResponse searchResponse = EsUtils.getClient().search(searchRequest, RequestOptions.DEFAULT);
        String scrollId = searchResponse.getScrollId();
        SearchHits hits = searchResponse.getHits();


        final Scroll scroll = new Scroll(TimeValue.timeValueMinutes(1L));
        searchRequest.scroll(scroll);

        SearchHit[] searchHits = searchResponse.getHits().getHits();

        while (searchHits != null && searchHits.length > 0) {

            SearchScrollRequest scrollRequest = new SearchScrollRequest(scrollId);
            scrollRequest.scroll(scroll);
            searchResponse = EsUtils.getClient().scroll(scrollRequest, RequestOptions.DEFAULT);
            scrollId = searchResponse.getScrollId();
            searchHits = searchResponse.getHits().getHits();
        }

        ClearScrollRequest clearScrollRequest = new ClearScrollRequest();
        clearScrollRequest.addScrollId(scrollId);
        ClearScrollResponse clearScrollResponse = EsUtils.getClient().clearScroll(clearScrollRequest, RequestOptions.DEFAULT);
        boolean succeeded = clearScrollResponse.isSucceeded();
    }

    public void multiSearch() throws IOException {
        MultiSearchRequest request = new MultiSearchRequest();
        SearchRequest firstSearchRequest = new SearchRequest();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("user", "kimchy"));
        firstSearchRequest.source(searchSourceBuilder);
        request.add(firstSearchRequest);

        SearchRequest secondSearchRequest = new SearchRequest();
        searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("user", "luca"));
        secondSearchRequest.source(searchSourceBuilder);
        request.add(secondSearchRequest);

        MultiSearchResponse response = EsUtils.getClient().msearch(request, RequestOptions.DEFAULT);
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
