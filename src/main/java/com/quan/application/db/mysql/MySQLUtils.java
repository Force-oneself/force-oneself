package com.quan.application.db.mysql;

import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

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
    private static final String MYSQL_URL = "jdbc:mysql://localhost:3306/cas" +
            "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false" +
            "&characterEncoding=utf8&useSSL=false&serverTimezone=UTC";

    public static final String SELECT_ALL_PREFIX = "SELECT * FROM ";

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

    /**
     * 获取结果元信息
     * @param tableName
     * @return
     */
    public static ResultSetMetaData getResultSetMetaData(String tableName) {
        Connection conn = getConnection();
        ResultSetMetaData rsmd = null;
        String tableSql =  SELECT_ALL_PREFIX + tableName;
        try {
            rsmd = conn.prepareStatement(tableSql).getMetaData();
        } catch (SQLException e) {
            log.error("getColumnNames failure", e);
        }
        return rsmd;
    }

    /**
     * 获取库里全部表名
     * @return
     */
    public static List<String> getTableNames() {
        List<String> tableNames = new ArrayList<>();
        Connection conn = getConnection();
        ResultSet rs = null;
        try {
            //获取数据库的元数据
            DatabaseMetaData db = conn.getMetaData();
            //从元数据中获取到所有的表名
            rs = db.getTables(null, null, null, new String[] { "TABLE" });
            while(rs.next()) {
                tableNames.add(rs.getString(3));
            }
        } catch (SQLException e) {
            log.error("getTableNames failure", e);
        } finally {
            try {
                rs.close();
                close(conn, null, null);
            } catch (SQLException e) {
                log.error("close ResultSet failure", e);
            }
        }
        return tableNames;
    }

    public static Map<String, String> getJavaWrapper() {
        Properties props;
        Map<String, String> wrapper;
        // 使用静态代码块为Properties对象赋值
            try {
                // 实例化对象
                props = new Properties();
                // 获取properties文件的流对象
                InputStream in = MySQLUtils.class.getClassLoader().getResourceAsStream("mysql-java-wrapper.properties");
                props.load(in);
                // 实例化容器
                wrapper = new HashMap<>();
                // 取出配置文件中所有的key
                Enumeration keys = props.keys();
                // 遍历枚举
                while (keys.hasMoreElements()) {
                    // 取出每个key
                    String key = keys.nextElement().toString();
                    // 根据key获取value
                    String value = props.getProperty(key);
                    // 把key和value存入容器
                    wrapper.put(key, value);
                }
            } catch (Exception e) {
                throw new ExceptionInInitializerError("初始化properties对象失败");
            }
        return wrapper;
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
