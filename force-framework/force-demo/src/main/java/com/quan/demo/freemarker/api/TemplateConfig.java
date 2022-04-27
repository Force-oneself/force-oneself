package com.quan.demo.freemarker.api;

/**
 * TemplateConfig
 *
 * @author Force-oneself
 * @date 2022-04-27
 */
public interface TemplateConfig extends Config {

    /**
     * 模版路径
     *
     * @return return
     */
    String templatePath();

    /**
     * 输出路径
     *
     * @return return
     */
    String outPath();

    /**
     * 编码
     *
     * @return return
     */
    String encoding();
}
