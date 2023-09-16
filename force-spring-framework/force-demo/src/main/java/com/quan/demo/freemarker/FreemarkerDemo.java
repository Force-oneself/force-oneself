package com.quan.demo.freemarker;

import com.quan.demo.freemarker.api.ConfigurableFreemarkerGenerator;
import com.quan.demo.freemarker.api.Generator;
import com.quan.demo.freemarker.api.TemplateConfig;
import com.quan.demo.freemarker.base.DefaultTemplateConfig;
import com.quan.demo.freemarker.support.JustNameGenerator;
import com.quan.demo.freemarker.support.mysql.MysqlPropertiesGenerator;
import com.quan.demo.freemarker.support.mysql.SimpleMysqlTemplateConfig;
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
        mysql();
    }

    private static void mysql() {
        SimpleMysqlTemplateConfig holder = new SimpleMysqlTemplateConfig();
        holder.setOutPrefixPath("/Users/forceoneself/IdeaProjects/force-to-live/force-framework/force-demo/src/main/java/com/quan/demo/freemarker/");
        holder.setTemplateFileName("entity");
        MysqlPropertiesGenerator generator = new MysqlPropertiesGenerator(Collections.singletonList(holder));
        generator.generate();
    }

    private static void justName() {
        DefaultTemplateConfig config = new DefaultTemplateConfig();
        config.setOutPrefixPath("/Users/forceoneself/IdeaProjects/force-to-live/force-framework/force-demo/src/main/java/com/quan/demo/freemarker/");
        config.setTemplateFileName("entity");
        JustNameGenerator generator = new JustNameGenerator(Collections.singletonList(config));
        generator.setClassName("Demo");
        generator.setPkg("com.quan.demo.freemarker");
        generator.generate();
    }

    private static void inner() {
        Generator generator = new ConfigurableFreemarkerGenerator<TemplateConfig>() {

            @Override
            public Object dataModel() {
                return param();
            }

            @Override
            public Collection<TemplateConfig> templateConfig() {
                DefaultTemplateConfig holder = new DefaultTemplateConfig();
                // Configuration未配置TemplateLoader需要配置全路径
//                holder.setTemplatePrefixPath("/force-framework/force-demo/src/main/resources/ftl/");
                holder.setTemplateFileName("entity-demo.ftl");
                holder.setOutPrefixPath("/Users/forceoneself/IdeaProjects/force-to-live/force-framework/force-demo/src/main/java/com/quan/demo/freemarker/");
                holder.setFileName("com.quan.demo.controller.User");
                return Collections.singleton(holder);
            }

//            @Override
//            public void configurationCustom(Configuration configuration) {
//            }

        };
        generator.generate();
    }

    public static Map<String, Object> param() {
        Map<String, Object> beanMap = new HashMap<>(16);
        // 实体类名
        beanMap.put("beanName", "com.quan.demo.controller.User");
        // 接口名
        beanMap.put("interfaceName", "com.quan.demo.controller.User");
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
