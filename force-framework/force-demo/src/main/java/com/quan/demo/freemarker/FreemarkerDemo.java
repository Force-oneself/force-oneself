package com.quan.demo.freemarker;

import com.quan.demo.freemarker.api.ConfigurableFreemarkerGenerator;
import com.quan.demo.freemarker.api.Generator;
import com.quan.demo.freemarker.api.TemplateConfig;
import com.quan.demo.freemarker.base.SimpleTemplateConfig;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.*;

/**
 * @author Force-oneself
 * @description FreemarkerDemo
 * @date 2021-12-26
 */
public class FreemarkerDemo {

    public static void main(String[] args) throws TemplateException, IOException {
        Generator generator = new ConfigurableFreemarkerGenerator() {

            @Override
            public Collection<TemplateConfig> templateConfig() {
                SimpleTemplateConfig holder = new SimpleTemplateConfig();
                // Configuration未配置TemplateLoader需要配置全路径
//                holder.setTemplatePrefixPath("/force-framework/force-demo/src/main/resources/ftl/");
                holder.setTemplatePath("entity.ftl");
                holder.setOutPrefixPath("/Users/forceoneself/IdeaProjects/force-to-live/force-framework/force-demo/src/main/java/com/quan/demo/freemarker/");
                holder.setOutPath("User.java");
                return Collections.singleton(holder);
            }

//            @Override
//            public void configurationCustom(Configuration configuration) {
//            }

            @Override
            public Object dataModel() {
                return param();
            }
        };
        generator.generate();
    }

    public static Map<String, Object> param() {
        Map<String, Object> beanMap = new HashMap<>(16);
        // 实体类名
        beanMap.put("beanName", "User");
        // 接口名
        beanMap.put("interfaceName", "User");
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


}
