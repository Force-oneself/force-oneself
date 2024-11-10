package com.force.log.datasource;


import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.quan.log.conf.ForceLogConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import({DruidDataSourceAutoConfigure.class, ForceLogConfiguration.class})
public class DataSourceTest {


    @Autowired
    private DataSource dataSource;

    @Test
    public void nativeLog() {
        Connection conn = null;
        Statement st = null;
        PreparedStatement ps = null;
        try {
            // 建立连接
            conn = dataSource.getConnection();
            // 准备SQL语句
            String sql = "SELECT * FROM flink_demo   " +
                    " WHERE id = ? AND name = ?";
            ps = conn.prepareStatement(sql);

            // 设置参数
            ps.setInt(1, 1);
            ps.setString(2, "zhangsan");

            // 执行SQL查询
            ps.executeQuery();

            // 执行查询
            //st.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void nativeQueryLog() {
        Connection conn = null;
        Statement st = null;
        PreparedStatement ps = null;
        try {
            // 建立连接
            conn = dataSource.getConnection();
            // 准备SQL语句
            String sql = "SELECT * FROM flink_demo";
            st = conn.createStatement();

            // 执行查询
            st.executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
