package com.quan.clickhouse.mapper;

import com.quan.clickhouse.entity.PricePaid;
import com.quan.clickhouse.enums.Duration;
import com.quan.clickhouse.enums.Type;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.EnumOrdinalTypeHandler;

import java.util.List;
import java.util.Map;

@Mapper
public interface DemoMapper {

    @Results(id = "list", value = {
            @Result(
                    column = "type",
                    property = "type",
                    javaType = Type.class,
                    typeHandler = EnumOrdinalTypeHandler.class
            ),
            @Result(
                    column = "duration",
                    property = "duration",
                    javaType = Duration.class,
                    typeHandler = EnumOrdinalTypeHandler.class
            )
    }
    )
    @Select("select * from uk_price_paid limit 10")
    List<PricePaid> list();

    @Select("select * from uk_price_paid limit 10")
    List<Map<String, Object>> listMap();

    @Insert({
            "INSERT INTO uk_price_paid (price, date, postcode1, postcode2, type, is_new, duration, addr1, addr2, street, locality, town, district, county) ",
            "VALUES (#{price}, #{date}, #{postcode1}, #{postcode2}, #{type.code}, #{isNew}, #{duration.code}, #{addr1}, #{addr2}, #{street}, #{locality}, #{town}, #{district}, #{county})"
    })
    boolean insert(PricePaid pricePaid);
}
