package com.force.log.mapper;

import com.force.log.entity.FlinkDemo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FlinkDemoMapper {
    @Select("SELECT * FROM flink_demo")
    List<FlinkDemo> findAll();
    
    @Select("SELECT * FROM flink_demo WHERE id = #{id}")
    FlinkDemo findById(@Param("id") Long id);
    
    @Insert("INSERT INTO flink_demo(name, age) VALUES(#{name}, #{age})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(FlinkDemo flinkDemo);
    
    @Update("UPDATE flink_demo SET name = #{name}, age = #{age} WHERE id = #{id}")
    int update(FlinkDemo flinkDemo);
    
    @Delete("DELETE FROM flink_demo WHERE id = #{id}")
    int deleteById(@Param("id") Long id);
} 