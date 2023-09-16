package com.quan.demo.freemarker.api;

import freemarker.template.Template;

import java.io.Writer;
import java.util.function.Supplier;

/**
 * TemplateBear
 *
 * @author Force-oneself
 * @date 2022-04-27
 */
public interface TemplateBear {

    /**
     * 模版生成器
     *
     * @return return
     */
    Supplier<Template> template();

    /**
     * 输出流生成器
     *
     * @return return
     */
    Supplier<Writer> out();

}
