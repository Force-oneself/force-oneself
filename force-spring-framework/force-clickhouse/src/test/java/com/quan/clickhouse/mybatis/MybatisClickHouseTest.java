package com.quan.clickhouse.mybatis;

import com.quan.clickhouse.entity.PricePaid;
import com.quan.clickhouse.enums.Duration;
import com.quan.clickhouse.enums.Type;
import com.quan.clickhouse.mapper.DemoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MybatisClickHouseTest {

    @Autowired
    public DemoMapper demoMapper;

    @Test
    public void listMap() {
        List<Map<String, Object>> list = demoMapper.listMap();
        System.out.println(list);
    }

    @Test
    public void list() {
        List<PricePaid> list = demoMapper.list();
        System.out.println(list);
    }

    @Test
    public void insert() {
        PricePaid ukPricePaid = new PricePaid();
        ukPricePaid.setPrice(250000);
        ukPricePaid.setDate(new Date());
        ukPricePaid.setPostcode1("qingtang");
        ukPricePaid.setPostcode2("1AA");
        ukPricePaid.setType(Type.DETACHED);
        ukPricePaid.setNew(true);
        ukPricePaid.setDuration(Duration.FREEHOLD);
        ukPricePaid.setAddr1("10 Downing Street");
        ukPricePaid.setAddr2("Westminster");
        ukPricePaid.setStreet("Downing Street");
        ukPricePaid.setLocality("City of Westminster");
        ukPricePaid.setTown("London");
        ukPricePaid.setDistrict("Westminster");
        ukPricePaid.setCounty("Greater London");
        boolean insert = demoMapper.insert(ukPricePaid);
    }
}
