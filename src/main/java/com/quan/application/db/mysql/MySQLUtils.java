package com.quan.application.db.mysql;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * MySQL base util
 *
 * @author Force-Oneself
 * @date 2020-04-30
 */
@Slf4j
public class MySQLUtils {

    private static final String MYSQL_USER_NAME = "root";
    private static final String MYSQL_PASSWORD = "root";
    private static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String MYSQL_URL = "jdbc:mysql://localhost:3306/cas?useSSL=FALSE&serverTimezone=UTC";

    /**
     * @param
     * @return java.sql.Connection
     * @Decription 获取数据库连接
     * @Author Force-Oneself
     * @Date 2020-04-30
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(MYSQL_DRIVER);
            connection = DriverManager.getConnection(MYSQL_URL, MYSQL_USER_NAME, MYSQL_PASSWORD);
        } catch (Exception e) {
            log.error("init failure ", e);
        }

        return connection;
    }

    public static ResultSetMetaData getResultSetMetaData(String tableName) {
        Connection conn = getConnection();
        ResultSetMetaData rsmd = null;
        String tableSql = "select * from " + tableName;
        try {
            rsmd = conn.prepareStatement(tableSql).getMetaData();
        } catch (SQLException e) {
            log.error("getColumnNames failure", e);
        }
        return rsmd;
    }

    /**
     * @param connection
     * @param ps
     * @param resultSet
     * @return void
     * @Decription 数据库关闭连接
     * @Author Force-Oneself
     * @Date 2020-04-30
     */
    public static void close(Connection connection, PreparedStatement ps, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            log.error("close failure ", e);
        }
    }
}
