package com.quan.demo.freemarker.api;

import com.quan.demo.freemarker.enums.InternalKeyEnum;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * AbstractFreemarkerGenerator
 *
 * @author Force-oneself
 * @date 2022-05-05
 */
public abstract class AbstractFreemarkerGenerator<T extends TemplateConfig>
        implements DataModel, ConfigurableFreemarkerGenerator<T> {

    protected final List<T> templateConfigs;
    protected Map<String, Object> data = new HashMap<>(16);

    protected AbstractFreemarkerGenerator(List<T> templateConfigs) {
        this.templateConfigs = templateConfigs;
    }

    {
        Map<String, String> env = System.getenv();
        // TODO 转换特殊字符
        data.put(InternalKeyEnum.SYSTEM_ENV.getKey(), env);
        data.put(InternalKeyEnum.SYSTEM_PROPERTIES.getKey(), System.getProperties());
    }

    /**
     * 默认实现
     *
     * @return return
     */
    @Override
    public Collection<T> templateConfig() {
        return templateConfigs;
    }

    /**
     * 实现统一封装数据模型
     *
     * @return return
     */
    @Override
    public Map<String, Object> dataModel() {
        data.put(InternalKeyEnum.ENTITY.getKey(), metaDefinition());
        return this.data;
    }

    /**
     * 元信息
     *
     * @return 类元信息
     */
    protected abstract ClassMetaDefinition metaDefinition();
}
