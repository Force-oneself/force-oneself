package com.quan.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author Force-oneself
 */
public final class SerializeUtils {

    /**
     * @param object
     * @return
     * @Description 序列化
     */
    public static byte[] serialize(Object object) {
        try (ByteArrayOutputStream bas = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bas)) {
            // 序列化
            oos.writeObject(object);
            return bas.toByteArray();
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * @param bytes
     * @return
     * @Description 反序列化
     */
    public static Object universalize(byte[] bytes) {

        try(ByteArrayInputStream bas = new ByteArrayInputStream(bytes)) {
            // 反序列化
            return new ObjectInputStream(bas).readObject();
        } catch (Exception e) {
        }
        return null;
    }
}
