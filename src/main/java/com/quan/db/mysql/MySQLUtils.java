package com.quan.db.mysql;

import java.sql.*;

/**
 * MySQL base util
 * @author Force-Oneself
 * @date 2020-04-30
 */
public class MySQLUtils {

    private static final String MYSQL_USER_NAME = "root";
    private static final String MYSQL_PASSWORD = "root";
    private static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String MYSQL_URL = "jdbc:mysql://localhost:3306/demo?useSSL=FALSE&serverTimezone=UTC";

    /**
     * @Decription 获取数据库连接
     * @Author Force-Oneself
     * @Date 2020-04-30
     * @param
     * @return java.sql.Connection
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(MYSQL_DRIVER);
            connection = DriverManager.getConnection(MYSQL_URL, MYSQL_USER_NAME, MYSQL_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return connection;
    }

    /**
     * @Decription 数据库关闭连接
     * @Author Force-Oneself
     * @Date 2020-04-30
     * @param connection
     * @param ps
     * @param resultSet
     * @return void
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
            e.printStackTrace();
        }

    }

}
