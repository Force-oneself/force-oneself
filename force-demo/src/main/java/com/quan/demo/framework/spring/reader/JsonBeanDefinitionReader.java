package com.quan.demo.framework.spring.reader;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.core.NamedThreadLocal;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description: class JsonBeanDefinitionReader
 * 类似
 * * {@link org.springframework.beans.factory.xml.XmlBeanDefinitionReader}
 * * 只是本类是去json文件里读取bean definition
 * @Author Force丶Oneself
 * @Date 2021-05-23
 **/
@Slf4j
public class JsonBeanDefinitionReader extends AbstractBeanDefinitionReader {
    private final ThreadLocal<Set<EncodedResource>> resourcesCurrentlyBeingLoaded =
            new NamedThreadLocal<>("json bean definition resources currently being loaded");


    public JsonBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    @Override
    public int loadBeanDefinitions(Resource resource) throws BeanDefinitionStoreException {
        // 以下照抄xmlbeanDefintionReader开始
        Set<EncodedResource> currentResources = this.resourcesCurrentlyBeingLoaded.get();
        if (currentResources == null) {
            currentResources = new HashSet<EncodedResource>(4);
            this.resourcesCurrentlyBeingLoaded.set(currentResources);
        }

        EncodedResource encodedResource = new EncodedResource(resource);
        if (!currentResources.add(encodedResource)) {
            throw new BeanDefinitionStoreException(
                    "Detected cyclic loading of " + encodedResource + " - check your import definitions!");
        }
        //照抄xmlbeanDefintionReader结束


        //这里的encodedResource.getResource()就是我们的json文件，这里通过spring core里面的一个工具类读取为InputStream
        String json = null;
        try (InputStream inputStream = encodedResource.getResource().getInputStream()) {
            json = StreamUtils.copyToString(inputStream, Charset.forName("UTF-8"));
        } catch (IOException e) {
            log.error("{}", e);
            return 0;
        } finally {
            currentResources.remove(encodedResource);
            if (currentResources.isEmpty()) {
                this.resourcesCurrentlyBeingLoaded.remove();
            }
        }

        //熟悉的fastjson，熟悉的味道
        List<GenericBeanDefinition> list = JSON.parseArray(json, GenericBeanDefinition.class);
        if (CollectionUtils.isEmpty(list)) {
            return 0;
        }

        /**
         * 1：因为GenericBeanDefinition，只有setBeanClassName，所以bean反序列化时，只序列化了这个字		* 段；实际我们知道，beanClass很重要，所以我们只能自己处理一下了
         * 2：第二个问题，我们在下面解释
         **/
        for (GenericBeanDefinition genericBeanDefinition : list) {
            /**
             * 1、处理beanClass
             */
            Class<?> clazz = null;
            try {
                clazz = Thread.currentThread().getContextClassLoader().loadClass(genericBeanDefinition.getBeanClassName());
            } catch (ClassNotFoundException e) {
                log.error("bean class cant be load for beandefinition: {}", genericBeanDefinition);
                throw new RuntimeException();
            }

            genericBeanDefinition.setBeanClass(clazz);

            /**
             * 2、处理constructor问题，因为Object value = valueHolder.getValue();
             * 是Object类型，但这个实际是一个可变类型，当构造器参数为String类型时，这个Object就是	              * String类型的，当构造器参数类型为其他bean的引用时，这个object就是RuntimeBeanReference              * 的，
             * 因为fastjson把我的object转成jsonobject类型了，所以这里要手动搞成RuntimeBeanReference
             */
            ConstructorArgumentValues constructorArgumentValues = genericBeanDefinition.getConstructorArgumentValues();
            if (constructorArgumentValues.isEmpty()) {
                continue;
            }
            Map<Integer, ConstructorArgumentValues.ValueHolder> map = constructorArgumentValues.getIndexedArgumentValues();
            if (CollectionUtils.isEmpty(map)) {
                continue;
            }
            for (ConstructorArgumentValues.ValueHolder valueHolder : map.values()) {
                Object value = valueHolder.getValue();
                if (value instanceof JSONObject) {
                    JSONObject jsonObject = (JSONObject) value;
                    RuntimeBeanReference runtimeBeanReference = jsonObject.toJavaObject(RuntimeBeanReference.class);
                    valueHolder.setValue(runtimeBeanReference);
                }
            }
        }

        //这里new一个BeanNameGenerator，这是自带的
        setBeanNameGenerator(new AnnotationBeanNameGenerator());
        BeanNameGenerator beanNameGenerator = getBeanNameGenerator();
        // 获取BeanDefinitionRegistry，bean factory默认实现了BeanDefinitionRegistry
        BeanDefinitionRegistry registry = getRegistry();
        //注册bean definition到BeanDefinitionRegistry里面去
        for (GenericBeanDefinition genericBeanDefinition : list) {
            String beanName = beanNameGenerator.generateBeanName(genericBeanDefinition, registry);
            registry.registerBeanDefinition(beanName, genericBeanDefinition);
        }

        return list.size();
    }
}
