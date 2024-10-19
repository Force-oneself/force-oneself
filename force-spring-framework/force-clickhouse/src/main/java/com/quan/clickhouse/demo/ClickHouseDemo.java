package com.quan.clickhouse.demo;

import com.quan.clickhouse.entity.PricePaid;
import com.quan.clickhouse.enums.Duration;
import com.quan.clickhouse.enums.Type;
import com.quan.clickhouse.util.CKUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class ClickHouseDemo {

    public static void main(String[] args) {

        List<Map<String, Object>> query = CKUtils.query("select * from uk_price_paid limit 10");
        String insertSql = "insert into uk_price_paid " +
                "(price,date,postcode1,postcode2,type,is_new,duration,addr1,addr2,street,locality,town,district,county)" +
                " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        CKUtils.update(insertSql, stat -> {
        });
        // 创建一个 UkPricePaid 对象
        PricePaid ukPricePaid = new PricePaid();
        ukPricePaid.setPrice(250000);
        ukPricePaid.setDate(new Date());
        ukPricePaid.setPostcode1("SW1A");
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

    }


}
