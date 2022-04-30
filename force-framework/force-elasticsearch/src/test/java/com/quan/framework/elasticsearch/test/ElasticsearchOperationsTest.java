package com.quan.framework.elasticsearch.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.GetQuery;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * ElasticsearchOperationsTest
 *
 * @author Force-oneself
 * @date 2022-04-30
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticsearchOperationsTest {

    @Autowired
    private ElasticsearchOperations elasticsearchRestTemplate;

    @Test
    public void save() {
        Person person = new Person();
        person.setId(1L);
        IndexQuery indexQuery = new IndexQueryBuilder()
                .withId(person.getId().toString())
                .withObject(person)
                .build();
        String documentId = elasticsearchRestTemplate.index(indexQuery, IndexCoordinates.of("person"));
    }

    @Test
    public void findById() {
        Person person = elasticsearchRestTemplate
                .queryForObject(GetQuery.getById("1"), Person.class);
        System.out.println(person);
    }

    @Document(indexName = "person")
    public static class Person {
        private Long id;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }
}
