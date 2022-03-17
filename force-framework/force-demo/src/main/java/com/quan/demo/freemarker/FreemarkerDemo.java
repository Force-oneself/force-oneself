package com.quan.demo.freemarker;

import com.quan.demo.freemarker.api.ConfigurableFreemarkerGenerator;
import com.quan.demo.freemarker.api.Generator;
import com.quan.demo.freemarker.api.TemplateGlobalConfig;
import com.quan.demo.freemarker.base.TemplateConfigHolder;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;

/**
 * @author Force-oneself
 * @description FreemarkerDemo
 * @date 2021-12-26
 */
public class FreemarkerDemo {

    public static void main(String[] args) throws TemplateException, IOException {
        Generator generator = new ConfigurableFreemarkerGenerator() {

            @Override
            public Collection<TemplateConfigHolder> configHolders() {
                TemplateConfigHolder holder = new TemplateConfigHolder();
                holder.setTemplatePath("entity.ftl");
                holder.setOutPath("User.java");
                return Collections.singleton(holder);
            }


            @Override
            public TemplateGlobalConfig globalConfig() {
                return new TemplateGlobalConfig() {
                    @Override
                    public Consumer<Configuration> customizeConfig() {
                        return null;
                    }

                    @Override
                    public String outPrefixPath() {
                        return "/Users/forceoneself/IdeaProjects/force-to-live/force-framework/force-demo/src/main/java/com/quan/demo/freemarker/";
                    }

                    @Override
                    public String templatePrefixPath() {
                        return "/force-framework/force-demo/src/main/resources/ftl/";
                    }
                };
            }

            @Override
            public Object dataModel() {
                return param();
            }
        };
        generator.generate();
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


}
