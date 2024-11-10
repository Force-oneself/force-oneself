package com.quan.log.data.datasource;

import java.sql.SQLException;

@FunctionalInterface
public interface SQLSupplier<T> {

    T get() throws SQLException;
}
