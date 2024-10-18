package com.quan.framework.elasticsearch.test;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
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
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.SuggestBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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

    public static final String ES_DEMO_INDEX = "hotel";
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Test
    public void createIndex() throws IOException {
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
    public void deleteIndex() throws IOException {
        restHighLevelClient.indices().delete(new DeleteIndexRequest(ES_DEMO_INDEX), RequestOptions.DEFAULT);
    }

    @Test
    public void updateIndex() throws IOException {
        String indexName = "your_index_name";
        Map<String, Object> properties = new HashMap<>();
        properties.put("type", "text");
        properties.put("analyzer", "standard");
        Map<String, Object> fieldToAdd = new HashMap<>();
        fieldToAdd.put("properties", Collections.singletonMap("new_field", properties));

        PutMappingRequest putMappingRequest = new PutMappingRequest(indexName);
        putMappingRequest.source(fieldToAdd, XContentType.JSON);
        restHighLevelClient.indices().putMapping(putMappingRequest);
    }

    @Test
    public void createDocument() throws IOException {

        // 创建 Hotel 对象
        Hotel hotel = new Hotel();
        hotel.setId("1");
        hotel.setName("Grand Hotel");
        hotel.setAddress("123 Main St");
        hotel.setCity("New York");
        hotel.setCountry("USA");
        hotel.setStars(5);
        hotel.setPricePerNight(200.0);
        hotel.setHasPool(true);
        hotel.setHasFreeWifi(true);

        // 将 Hotel 对象转换为 JSON 字符串
        String jsonString = hotelToJson(hotel);

        // 创建 IndexRequest
        IndexRequest indexRequest = new IndexRequest(ES_DEMO_INDEX)
                .id(hotel.getId())
                .source(jsonString, XContentType.JSON);

        // 执行索引操作
        IndexResponse indexResponse;
        indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        if (indexResponse.getResult() == IndexResponse.Result.CREATED) {
            System.out.println("文档创建成功");
        } else if (indexResponse.getResult() == IndexResponse.Result.UPDATED) {
            System.out.println("文档更新成功");
        } else {
            System.out.println("文档操作失败");
        }

    }

    // 将 Hotel 对象转换为 JSON 字符串
    private static String hotelToJson(Hotel hotel) {
        return "{\n" +
                "  \"id\": \"" + hotel.getId() + "\",\n" +
                "  \"name\": \"" + hotel.getName() + "\",\n" +
                "  \"address\": \"" + hotel.getAddress() + "\",\n" +
                "  \"city\": \"" + hotel.getCity() + "\",\n" +
                "  \"country\": \"" + hotel.getCountry() + "\",\n" +
                "  \"stars\": " + hotel.getStars() + ",\n" +
                "  \"pricePerNight\": " + hotel.getPricePerNight() + ",\n" +
                "  \"hasPool\": " + hotel.isHasPool() + ",\n" +
                "  \"hasFreeWifi\": " + hotel.isHasFreeWifi() + "\n" +
                "}";
    }


    @Test
    public void search() throws IOException {
        // match_all
        this.search(QueryBuilders.matchAllQuery());
        // multi_match
        this.search(QueryBuilders.multiMatchQuery("query", "name", "age"));
        // term 关键字
        this.search(QueryBuilders.termQuery("firstname", "Parker"));
        // bool 多条件
        this.search(QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("must", "must"))
                .should(QueryBuilders.matchQuery("should", "shoule"))
                .mustNot(QueryBuilders.matchQuery("must_not", "must_not"))
                .filter(QueryBuilders.matchQuery("filter", "filter")));

        // prefix 前缀
        this.search(QueryBuilders.prefixQuery("address", "990"));
        // wildcard 通配符
        this.search(QueryBuilders.wildcardQuery("city", "Lop*"));
        // range 范围
        this.search(QueryBuilders.rangeQuery("balance").gt(9800).lt(10000));
        // ids
        this.search(QueryBuilders.idsQuery().addIds("136", "970"));
        // matchPhrase 短词
        this.search(QueryBuilders.matchPhraseQuery("email", "forbeswallace@pheast.com"));
        // highlight
        this.search(new SearchSourceBuilder()
                .highlighter(SearchSourceBuilder
                        .highlight()
                        .field("name")
                        .requireFieldMatch(false)
                        .postTags("</em>").preTags("<em>"))
        );

        // from size
        this.search(new SearchSourceBuilder().from(0).size(100));
        // sort
        this.search(new SearchSourceBuilder().sort("name", SortOrder.DESC));

        // fetch_source
        this.search(new SearchSourceBuilder().fetchSource("name", "age"));

        // aggregation
        this.search(new SearchSourceBuilder()
                .aggregation(AggregationBuilders.terms("age")
                        .size(20)
                        .order(BucketOrder.aggregation("_count", false))
                )
        );
        // 自动补全
        this.search(new SearchSourceBuilder()
                .suggest(new SuggestBuilder()
                        .addSuggestion("s", SuggestBuilders.completionSuggestion("suggestion").size(10).skipDuplicates(true)))
        );
    }
    private void search(QueryBuilder query) throws IOException {
        this.search(new SearchSourceBuilder().query(query));
    }

    private void search(SearchSourceBuilder searchSourceBuilder) throws IOException {
        SearchRequest request = new SearchRequest(ES_DEMO_INDEX)
                .source(searchSourceBuilder);
        SearchResponse search = restHighLevelClient.search(request, RequestOptions.DEFAULT);
        Arrays.stream(search.getHits().getHits())
                .map(SearchHit::getSourceAsString)
                .forEach(System.out::println);
    }

    public class Hotel {

        private String id;
        private String name;
        private String address;
        private String city;
        private String country;
        private int stars;
        private double pricePerNight;
        private boolean hasPool;
        private boolean hasFreeWifi;

        // 默认构造函数
        public Hotel() {
        }

        // Getter 和 Setter 方法
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public int getStars() {
            return stars;
        }

        public void setStars(int stars) {
            this.stars = stars;
        }

        public double getPricePerNight() {
            return pricePerNight;
        }

        public void setPricePerNight(double pricePerNight) {
            this.pricePerNight = pricePerNight;
        }

        public boolean isHasPool() {
            return hasPool;
        }

        public void setHasPool(boolean hasPool) {
            this.hasPool = hasPool;
        }

        public boolean isHasFreeWifi() {
            return hasFreeWifi;
        }

        public void setHasFreeWifi(boolean hasFreeWifi) {
            this.hasFreeWifi = hasFreeWifi;
        }

        @Override
        public String toString() {
            return "Hotel{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", address='" + address + '\'' +
                    ", city='" + city + '\'' +
                    ", country='" + country + '\'' +
                    ", stars=" + stars +
                    ", pricePerNight=" + pricePerNight +
                    ", hasPool=" + hasPool +
                    ", hasFreeWifi=" + hasFreeWifi +
                    '}';
        }
    }

}
