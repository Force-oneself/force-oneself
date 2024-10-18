package com.quan.framework.elasticsearch.test;

import com.sun.jna.WString;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
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
    public void index() {
        Person person = new Person();
        person.setId(1L);
        IndexQuery indexQuery = new IndexQueryBuilder()
                .withId(person.getId().toString())
                .withObject(person)
                .build();
        // String documentId = elasticsearchRestTemplate.index(indexQuery, IndexCoordinates.of("person"));
    }

    @Test
    public void save() {
        Person person = new Person();
        person.setId(1L);
        person.setName("zhangsan");
        person.setAddress("北京");
        person.setAge(30);
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

        @Field(type = FieldType.Text)
        private String name;
        @Field(type = FieldType.Text)
        private String address;

        @Field(type = FieldType.Integer)
        private Integer age;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
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

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }
}
