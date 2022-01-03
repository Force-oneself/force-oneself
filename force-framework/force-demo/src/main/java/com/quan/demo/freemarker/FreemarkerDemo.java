package com.quan.demo.freemarker;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Force-oneself
 * @description FreemarkerDemo
 * @date 2021-12-26
 */
public class FreemarkerDemo {

    public static void main(String[] args) throws TemplateException, IOException {

        gen(param());
    }

    public static Map<String, Object> param() {
        Map<String, Object> beanMap = new HashMap<>();
        beanMap.put("beanName", "User");// 实体类名
        beanMap.put("interfaceName", "User");// 接口名
        List<Map<String, String>> paramsList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Map<String, String> tmpParamMap = new HashMap<>();
            tmpParamMap.put("fieldNote", "fieldNote" + i);
            tmpParamMap.put("fieldType", "String");
            tmpParamMap.put("fieldName", "fieldName" + i);
            paramsList.add(tmpParamMap);
        }
        beanMap.put("params", paramsList);
        return beanMap;
    }

    public static void gen(Map<String, Object> rootMap) throws IOException, TemplateException {
        Configuration config = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        config.setObjectWrapper(new DefaultObjectWrapper(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS));
        config.setTemplateLoader(new ClassTemplateLoader(FreemarkerDemo.class, "/ftl"));

        Template template = config.getTemplate("/entity.ftl", "UTF-8");
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("/Users/forceoneself/IdeaProjects/force-to-live/force-framework/force-demo/src/main/java/com/quan/demo/freemarker/User.java"), StandardCharsets.UTF_8));
        template.process(rootMap, out);
        out.flush();
        out.close();

        // 生成数据模版
        // 构建模版生成文件
    }


}
