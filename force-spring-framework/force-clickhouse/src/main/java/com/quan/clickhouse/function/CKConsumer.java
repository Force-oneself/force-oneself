package com.quan.clickhouse.function;

import java.sql.SQLException;
import java.util.Objects;
import java.util.function.Consumer;

@FunctionalInterface
public interface CKConsumer<T> {

    void accept(T t) throws SQLException;

    default Consumer<T> andThen(Consumer<? super T> after) {
        Objects.requireNonNull(after);
        return (T t) -> {
            try {
                accept(t);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            after.accept(t);
        };
    }
}
