package com.quan.demo.framework.spring.context;

import com.quan.demo.framework.spring.reader.JsonBeanDefinitionReader;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractRefreshableConfigApplicationContext;

import java.io.IOException;

/**
 * @Description: class ClassPathJsonApplicationContext
 * @Author Force丶Oneself
 * @Date 2021-05-23
 **/
public class ClassPathJsonApplicationContext extends AbstractRefreshableConfigApplicationContext {

    /**
     * 构造方法，默认一些配置
     *
     * @param configLocation 配置位置
     * @throws BeansException Bean相关异常
     */
    public ClassPathJsonApplicationContext(String configLocation) throws BeansException {
        this(new String[]{configLocation}, true, null);
    }

    /**
     * 与xml一模一样
     *
     * @param configLocations 配置位置
     * @param refresh         刷新容器
     * @param parent          父容器
     * @throws BeansException Bean相关异常
     */
    public ClassPathJsonApplicationContext(String[] configLocations, boolean refresh, ApplicationContext parent)
            throws BeansException {

        super(parent);
        this.setConfigLocations(configLocations);
        if (refresh) {
            refresh();
        }
    }

    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) throws BeansException, IOException {
        //其实主要内容和xmlApplicationContext是一样的，主要就是下面这行不一样，new了一个json reader
        JsonBeanDefinitionReader beanDefinitionReader = new JsonBeanDefinitionReader(beanFactory);
        // 设置环境
        beanDefinitionReader.setEnvironment(this.getEnvironment());
        beanDefinitionReader.setResourceLoader(this);
        // 这里通过JsonBeanDefinitionReader去读取bean definition
        this.loadBeanDefinitions(beanDefinitionReader);
    }

    /**
     * 通过json bean definition reader去读取bean definition
     **/
    protected void loadBeanDefinitions(JsonBeanDefinitionReader reader) throws BeansException, IOException {
        // 这里获取json文件的path，这个location是在new ClassPathJsonApplicationContext时传进来的
        String[] configResources = this.getConfigLocations();
        if (configResources != null) {
            reader.loadBeanDefinitions(configResources);
        }
    }

}

