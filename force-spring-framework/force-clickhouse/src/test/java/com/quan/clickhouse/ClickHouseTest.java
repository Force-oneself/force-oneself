package com.quan.clickhouse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClickHouseTest {

    @Autowired
    public JdbcTemplate jdbcTemplate;

    @Test
    public void select() {
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from java_test limit 2");
        System.out.println(maps);
    }

    @Test
    public void insert() {
        int hello = jdbcTemplate.update(
                "insert into java_test(id, name, create_time, county, age) values(?, ?, ?, ?, ?);",
                1, "hello", new Date(), "china", 18);
        System.out.println(hello);
    }
}
