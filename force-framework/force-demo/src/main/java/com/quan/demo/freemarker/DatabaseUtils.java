package com.quan.demo.freemarker;

import com.quan.demo.freemarker.meta.ColumnMeta;
import com.quan.demo.freemarker.meta.DefaultColumnMeta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author Force-oneself
 * @description DatabaseUtils
 * @date 2021-12-30
 */
public class DatabaseUtils {

    private final static Logger LOGGER = LoggerFactory.getLogger(DatabaseUtils.class);

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/javaweb?useUnicode=true&characterEncoding=utf8";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "mysql";

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            LOGGER.error("can not load jdbc driver", e);
        }
    }

    /**
     * 获取数据库连接
     *
     * @return
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            LOGGER.error("get connection failure", e);
        }
        return conn;
    }

    public static Connection getConnection(String url, String username, String password) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            LOGGER.error("get connection failure", e);
        }
        return conn;
    }

    /**
     * 获取数据库下的所有表名
     */
    public static List<String> getTableNames() {
        List<String> tableNames = new ArrayList<>();
        try (Connection conn = getConnection();
             ResultSet rs = conn.getMetaData().getTables(null, null, null, new String[]{"TABLE"})) {
            while (rs.next()) {
                tableNames.add(rs.getString(3));
            }
        } catch (SQLException e) {
            LOGGER.error("getTableNames failure", e);
        }
        return tableNames;
    }

    public static List<ColumnMeta> getColumnMeta(String tableName) {
        return getColumnMeta(tableName, DatabaseUtils::getConnection);
    }

    public static List<ColumnMeta> getColumnMeta(String tableName, Supplier<Connection> connectionSupplier) {
        ArrayList<ColumnMeta> metas = new ArrayList<>();
        try (Connection connection = connectionSupplier.get();
             ResultSet rs = connection.getMetaData().getColumns(null, "%", tableName, "%")) {
            int index = 0;
            while (rs.next()) {
                index++;
                DefaultColumnMeta meta = new DefaultColumnMeta();
                meta.set("COLUMN_INDEX", index);
                meta.set("COLUMN_NAME", rs.getString("COLUMN_NAME"));
                // 数据类型
                meta.set("TYPE_NAME", rs.getString("TYPE_NAME"));
                // 描述
                meta.set("REMARKS", rs.getString("REMARKS"));
                metas.add(meta);
            }
        } catch (SQLException e) {
            LOGGER.error("数据获取异常", e);
        }
        return metas;
    }
}
