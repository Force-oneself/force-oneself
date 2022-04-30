package com.quan.demo.freemarker.api;

/**
 * TemplateConfig
 *
 * @author Force-oneself
 * @date 2022-04-27
 */
public interface TemplateConfig extends Config {

    /**
     * 默认编码格式
     */
    String DEFAULT_ENCODING = "UTF-8";

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
    default String encoding() {
        return DEFAULT_ENCODING;
    }

}
