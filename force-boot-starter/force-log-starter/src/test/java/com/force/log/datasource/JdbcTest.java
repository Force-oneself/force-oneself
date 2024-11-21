package com.force.log.datasource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JdbcTest {

    @Autowired
    private JdbcTemplate template;


    @Test
    public void jdbcTest(){
        List<Map<String, Object>> maps = template.queryForList("select * from flink_demo");
        System.out.println(maps);
        List<Map<String, Object>> mapList = template.queryForList("select * from flink_demo where id=? and name=?", 1, "zhangsan");
        System.out.println(mapList);


    }
}
