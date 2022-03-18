package com.quan.demo.freemarker.support;

import com.quan.demo.freemarker.api.ConfigurableFreemarkerGenerator;
import com.quan.demo.freemarker.api.DriverDataModel;
import com.quan.demo.freemarker.base.TemplateConfigHolder;

import java.util.Collection;

/**
 * @author Force-oneself
 * @description SimpleFreemarkerGenerator
 * @date 2022-03-18
 */
public class SimpleFreemarkerGenerator implements ConfigurableFreemarkerGenerator, DriverDataModel {

    @Override
    public Collection<TemplateConfigHolder> configHolders() {
        return null;
    }
}
