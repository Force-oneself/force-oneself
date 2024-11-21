package com.force.log.datasource;

import com.force.log.entity.FlinkDemo;
import com.force.log.mapper.FlinkDemoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@Transactional
public class MyBatisTest {

    @Autowired
    private FlinkDemoMapper flinkDemoMapper;

    @Test
    public void testInsert() {
        FlinkDemo flinkDemo = new FlinkDemo();
        flinkDemo.setName("测试用户");
        flinkDemo.setAge(25);

        int result = flinkDemoMapper.insert(flinkDemo);
        assertEquals(1, result);
        assertNotNull(flinkDemo.getId(), "插入后的ID不应为空");
    }

    @Test
    public void testFindById() {
        FlinkDemo flinkDemo = new FlinkDemo();
        flinkDemo.setName("测试用户");
        flinkDemo.setAge(25);
        flinkDemoMapper.insert(flinkDemo);

        FlinkDemo found = flinkDemoMapper.findById(flinkDemo.getId());
        assertNotNull(found);
        assertEquals(flinkDemo.getName(), found.getName());
        assertEquals(flinkDemo.getAge(), found.getAge());
    }

    @Test
    public void testFindAll() {
        FlinkDemo flinkDemo1 = new FlinkDemo();
        flinkDemo1.setName("用户1");
        flinkDemo1.setAge(25);
        flinkDemoMapper.insert(flinkDemo1);

        FlinkDemo flinkDemo2 = new FlinkDemo();
        flinkDemo2.setName("用户2");
        flinkDemo2.setAge(30);
        flinkDemoMapper.insert(flinkDemo2);

        List<FlinkDemo> demos = flinkDemoMapper.findAll();
        assertNotNull(demos);
        assertTrue(demos.size() >= 2);
    }

    @Test
    public void testUpdate() {
        FlinkDemo flinkDemo = new FlinkDemo();
        flinkDemo.setName("原始用户");
        flinkDemo.setAge(25);
        flinkDemoMapper.insert(flinkDemo);

        flinkDemo.setName("更新后的用户");
        flinkDemo.setAge(26);
        int result = flinkDemoMapper.update(flinkDemo);
        assertEquals(1, result);

        FlinkDemo updated = flinkDemoMapper.findById(flinkDemo.getId());
        assertEquals("更新后的用户", updated.getName());
        assertEquals(26, updated.getAge());
    }

    @Test
    public void testDelete() {
        FlinkDemo flinkDemo = new FlinkDemo();
        flinkDemo.setName("待删除用户");
        flinkDemo.setAge(25);
        flinkDemoMapper.insert(flinkDemo);

        int result = flinkDemoMapper.deleteById(flinkDemo.getId());
        assertEquals(1, result);

        FlinkDemo deleted = flinkDemoMapper.findById(flinkDemo.getId());
        assertNull(deleted);
    }
} 