package com.quan.demo.freemarker.support.mysql;

import com.quan.demo.freemarker.enums.StringPool;

/**
 * @author Force-oneself
 * @description DriverConfigHolder
 * @date 2022-03-18
 */
public class DriverConfigHolder {

    protected String driver;

    protected String url;

    protected String username;

    protected String password;

    protected String basePackage;

    protected String tableName;

    protected String prefix = StringPool.EMPTY;

    protected String fileName = StringPool.EMPTY;

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
