package com.quan.framework.mongo.builder;

import org.springframework.data.mongodb.core.query.Update;

import java.util.function.Consumer;

/**
 * @author Force-oneself
 * @description UpdateBuilder
 * @date 2022-01-14
 */
public class Updates {

    private final Update update;

    public static Updates of() {
        return new Updates();
    }

    public static Updates of(Update update) {
        return new Updates(update);
    }

    public Updates() {
        this.update = new Update();
    }

    public Updates(Update update) {
        this.update = update == null ? new Update() : update;
    }

    public Update update() {
        return this.update;
    }

    public Updates and(boolean cond, Consumer<Update> consumer) {
        if (cond) {
            consumer.accept(this.update);
        }
        return this;
    }

    public Updates set(boolean cond, String key, Object value) {
        return and(cond, up -> up.set(key, value));
    }

    public Updates set(String key, Object value) {
        return set(true, key, value);
    }

    public Updates setOnInsert(boolean cond, String key, Object value) {
        return and(cond, up -> up.setOnInsert(key, value));
    }

    public Updates setOnInsert(String key, Object value) {
        return setOnInsert(true, key, value);
    }

    public Updates addToSet(boolean cond, String key, Object value) {
        return and(cond, up -> up.addToSet(key, value));
    }

    public Updates addToSet(String key, Object value) {
        return addToSet(true, key, value);
    }

    public Updates max(boolean cond, String key, Object value) {
        return and(cond, up -> up.max(key, value));
    }

    public Updates max(String key, Object value) {
        return max(true, key, value);
    }

    public Updates min(boolean cond, String key, Object value) {
        return and(cond, up -> up.min(key, value));
    }

    public Updates min(String key, Object value) {
        return min(true, key, value);
    }

    public Updates isIsolated(boolean cond) {
        return and(cond, Update::isIsolated);
    }


    public Updates inc(boolean cond, String key, Number value) {
        return and(cond, up -> up.inc(key, value));
    }

    public Updates inc(String key, Number value) {
        return inc(true, key, value);
    }

    public Updates multiply(boolean cond, String key, Number value) {
        return and(cond, up -> up.multiply(key, value));
    }

    public Updates multiply(String key, Number value) {
        return multiply(true, key, value);
    }

    public Updates pull(boolean cond, String key, Object value) {
        return and(cond, up -> up.pull(key, value));
    }

    public Updates pull(String key, Object value) {
        return pull(true, key, value);
    }

    public Updates pullAll(boolean cond, String key, Object[] value) {
        return and(cond, up -> up.pullAll(key, value));
    }

    public Updates pullAll(String key, Object[] value) {
        return pullAll(true, key, value);
    }

    public Updates rename(boolean cond, String oldName, String newName) {
        return and(cond, up -> up.rename(oldName, newName));
    }

    public Updates rename(String oldName, String newName) {
        return rename(true, oldName, newName);
    }

    public Updates pop(boolean cond, String key, Update.Position value) {
        return and(cond, up -> up.pop(key, value));
    }

    public Updates pop(String key, Update.Position value) {
        return pop(true, key, value);
    }

    public Updates currentDate(boolean cond, String key) {
        return and(cond, up -> up.currentDate(key));
    }

    public Updates currentDate(String key) {
        return currentDate(true, key);
    }

    public Updates currentTimestamp(boolean cond, String key) {
        return and(cond, up -> up.currentTimestamp(key));
    }

    public Updates currentTimestamp(String key) {
        return currentTimestamp(true, key);
    }

}
