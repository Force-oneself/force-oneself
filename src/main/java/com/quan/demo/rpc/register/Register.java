package com.quan.demo.rpc.register;


import com.quan.demo.rpc.framework.URL;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yuk on 2018/12/31.
 */
public class Register {

    private static Map<String, Map<URL, Class>> REGISTER = new HashMap<String, Map<URL, Class>>();

    /**
     * 注册服务（暴露接口）
     *
     * @param url
     * @param interfaceName
     * @param implClass
     */
    public static void register(URL url, String interfaceName, Class implClass) {
        Map<URL, Class> map = new HashMap<>();
        map.put(url, implClass);
        REGISTER.put(interfaceName, map);

        // 写入文本
        saveFile();
    }

    /**
     * 从注册中心获取实现类（发现服务）
     *
     * @param url
     * @param interfaceName
     * @return
     */
    public static Class get(URL url, String interfaceName) {
        return REGISTER.get(interfaceName).get(url);
    }

    /**
     * 模拟负载均衡，随机获取服务器
     *
     * @param interfaceName
     * @return
     */
    public static URL random(String interfaceName) {
        REGISTER = getFile();
        return REGISTER.get(interfaceName) != null ? REGISTER.get(interfaceName).keySet().iterator().next() : null;
    }

    /**
     * 写入文本
     */
    public static void saveFile() {
        try {
            FileOutputStream fos = new FileOutputStream("E://register.text");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(REGISTER);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取文本
     *
     * @return
     */
    public static Map<String, Map<URL, Class>> getFile() {
        try {
            FileInputStream fis = new FileInputStream("E://register.text");
            ObjectInputStream ois = new ObjectInputStream(fis);
            return (Map<String, Map<URL, Class>>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return Collections.emptyMap();
    }

    public static Class getClass(URL url, String interfaceName) {
            return getFile().get(interfaceName).get(url);
    }

}
