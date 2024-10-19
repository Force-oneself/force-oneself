package com.quan.clickhouse.util;

import com.clickhouse.jdbc.ClickHouseConnection;
import com.clickhouse.jdbc.ClickHouseDriver;
import com.quan.clickhouse.function.CKConsumer;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class CKUtils {

    /**
     * 默认压缩算法: LZ4
     */
    public static final String DEFAULT_URL = "jdbc:clickhouse://10.0.0.129:8123/demo";
    /**
     * 使用GZIP压缩算法
     */
    public static final String GZIP_URL = "jdbc:clickhouse://10.0.0.129:8123/demo?compress=1&compress_algorithm=gzip";
    /**
     * 不使用压缩算法
     */
    public static final String UNZIP_URL = "jdbc:clickhouse://10.0.0.129:8123/demo?compress=0";

    /**
     * 获取连接
     *
     * @return ClickHouseConnection
     * @throws SQLException 异常
     */
    public static ClickHouseConnection getConnection() throws SQLException {
        ClickHouseDriver driver = new ClickHouseDriver();
        return driver.connect(UNZIP_URL, null);
    }

    /**
     * 执行查询语句
     *
     * @param sql      sql语句
     * @param consumer 结果集处理器
     */
    public static void query(String sql, CKConsumer<ResultSet> consumer) {
        try (Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            consumer.accept(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Map<String, Object>> query(String sql) {
        try (ClickHouseConnection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            // 获取 ResultSetMetaData
            ResultSetMetaData metaData = resultSet.getMetaData();
            // 获取字段数
            int columnCount = metaData.getColumnCount();
            List<Map<String, Object>> res = new ArrayList<>();
            while (resultSet.next()) {
                HashMap<String, Object> resMap = new HashMap<>(columnCount);
                for (int i = 1; i <= columnCount; i++) {
                    resMap.put(metaData.getColumnName(i), resultSet.getObject(i));
                }
                res.add(resMap);
            }
            return res;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean update(String sql, CKConsumer<PreparedStatement> consumer) {

        try (ClickHouseConnection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            consumer.accept(statement);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
