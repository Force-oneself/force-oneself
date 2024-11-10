package com.quan.log.data.datasource;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Recorder {

    private final Logger log = LoggerFactory.getLogger(Recorder.class);

    /**
     * 预处理SQL
     */
    private String sql;
    /**
     * 執行SQL
     */
    private String lastExecuteSql;

    /**
     * 批量SQL
     */
    private List<String> batchSqlList;

    /**
     * 执行类型
     */
    private ExecuteType executeType;

    /**
     * 预处理SQL执行
     */
    private final PreparedStatement preparedStatement;

    /**
     * 正常SQL语句执行
     */
    private final Statement statement;

    /**
     * 预处理SQL设置参数
     */
    private final List<Object> parameters;

    /**
     * 执行SQL开始时间
     */
    private long start = -1;

    /**
     * 执行SQL结束时间
     */
    private long end = -1;

    public Recorder(Statement ps) {
        this.statement = ps;
        this.preparedStatement = PreparedStatement.class.isAssignableFrom(ps.getClass())
                ? (PreparedStatement) ps : null;
        this.parameters = new ArrayList<>();
    }

    public void recordSql(String sql) {
        this.sql = sql;
    }

    public void addBatch(String sql) {
        if (batchSqlList == null) {
            batchSqlList = new ArrayList<>();
        }
        batchSqlList.add(sql);
    }

    public <T> T execute(SQLSupplier<T> execute, String sql, ExecuteType executeType) throws SQLException {
        this.lastExecuteSql = sql;
        this.executeType = executeType;
        this.start = System.nanoTime();
        T resultSet = execute.get();
        this.end = System.nanoTime();
        this.print();
        return resultSet;
    }

    public void clearBatch() {
        if (this.batchSqlList == null) {
            return;
        }
        this.batchSqlList.clear();
    }

    public void clearParameters() {
        parameters.clear();
    }

    public void setParameter(Object parameter) {
        this.parameters.add(parameter);
    }

    public void print() throws SQLException {
        String finalSql = this.getSql();
        String sqlLogger = "\n\n============================  Sql Start  ============================" +
                "\nExecute SQL : {}" +
                "\nExecute Time: {}" +
                "\n============================  Sql  End   ============================\n";
        log.info(sqlLogger, finalSql.trim(), ((end - start) / 1000_000) + " ms");
    }

    /**
     * 获取最终SQL
     *
     * @return 最终SQL
     */
    private String getSql() {
        String finalSql = sql;
        // 批量SQL
        if (batchSqlList != null) {
            finalSql = String.join("\n;\n", batchSqlList);
        }
        // 非预处理SQL
        if (preparedStatement == null) {
            finalSql = lastExecuteSql;
        }
        return replacePrep(finalSql);
    }

    /**
     * 替换预处理SQL中的参数
     *
     * @param sql 未处理SQL
     * @return 可执行SQL
     */
    private String replacePrep(String sql) {
        for (Object param : this.parameters) {
            String replace = String.valueOf(param);
            if (param instanceof CharSequence) {
                replace = "'" + param + "'";
            }
            sql = sql.replaceFirst("\\?", replace);
        }
        // 去除多余的空格
        sql = sql.replaceAll("\\s+", " ").trim();
        return sql;
    }

}
