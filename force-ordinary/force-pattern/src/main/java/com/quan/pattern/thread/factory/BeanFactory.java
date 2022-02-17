package com.quan.pattern.thread.factory;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author Force-Oneself
 * @date 2020-04-30
 */
public class BeanFactory {

    // 定义一个Map
    private static final Map<String, Object> beans;
    // 定义一个bean容器是否初始化完成的标识
    private static int initStatus;

    private static final String BEAN_PROPERTIES_PATH = "bean.properties";

    // 使用静态代码块为Properties对象赋值
    static {
        try {
            // 定义一个properties对象
            Properties props = new Properties();
            // 获取properties文件的流对象
            InputStream in = BeanFactory.class.getClassLoader().getResourceAsStream(BEAN_PROPERTIES_PATH);
            props.load(in);
            // 实例化容器
            beans = new HashMap<>();
            // 取出配置文件中所有的key
            Enumeration<Object> keys = props.keys();
            // 遍历枚举
            while (keys.hasMoreElements()) {
                // 取出每个key
                String key = keys.nextElement().toString();
                // 首先查看容器中是否存在该对象
                Object obj = beans.get(key);
                if (obj == null) {
                    // 根据key获取value
                    String beanPath = props.getProperty(key);
                    // 反射创建对象
                    Object value = Class.forName(beanPath).newInstance();
                    // 把key和value存入容器
                    beans.put(key, value);
                }
            }
            initStatus = 1;
        } catch (Exception e) {
            initStatus = -1;
            throw new ExceptionInInitializerError("初始化properties对象失败");
        }
    }

    /**
     * 获取bean实例对象（单例）
     * 在bean容器在初始化加载时，存在在实例化bean的成员变量中也需要实例化。因此需要先实例化成员变量在才能实例化bean。
     * initStatus的作用是为了区分bean容器是否加载完成，当用户调用该方法时则不需要再次实例化bean，而是直接以bean容器
     * 为准。当系统在加载bean容器时如果调用该方法说明该bean中存在成员变量同样需要实例化。
     *
     * @param beanName
     * @return
     */
    public static Object getBean(String beanName) {
        Object object = beans.get(beanName);
        if (initStatus == 0 && object == null) {
            object = classForName(beanName);
            beans.put(beanName, object);
        }
        return object;
    }

    /**
     * 获取bean实例对象（多例）
     *
     * @param beanName
     * @param isMulti
     * @return
     */
    public static Object getBean(String beanName, boolean isMulti) {
        return isMulti ? classForName(beanName) : getBean(beanName);
    }

    /**
     * 反射创建实例对象
     *
     * @param name
     * @return
     */
    private static Object classForName(String name) {
        Object object = null;
        try {
            object = Class.forName(name).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

}
