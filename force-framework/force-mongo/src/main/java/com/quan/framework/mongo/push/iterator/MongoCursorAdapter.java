package com.quan.framework.mongo.push.iterator;

import com.mongodb.client.MongoCursor;

import java.io.IOException;
import java.util.function.Consumer;

/**
 * @author Force-oneself
 * @Description MongoCursorAdapter
 * @date 2021-09-15
 */
public class MongoCursorAdapter<T> implements IteratorAdapter<T>{

    private final MongoCursor<T> cursor;

    public MongoCursorAdapter(MongoCursor<T> cursor) {
        this.cursor = cursor;
    }

    @Override
    public void close() throws IOException {
        cursor.close();
    }

    @Override
    public boolean hasNext() {
        return cursor.hasNext();
    }

    @Override
    public T next() {
        return cursor.next();
    }

    @Override
    public void remove() {
        cursor.remove();
    }

    @Override
    public void forEachRemaining(Consumer<? super T> action) {
        cursor.forEachRemaining(action);
    }
}
