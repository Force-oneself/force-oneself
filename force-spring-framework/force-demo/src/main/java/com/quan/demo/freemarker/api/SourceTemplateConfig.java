package com.quan.demo.freemarker.api;

import com.quan.demo.freemarker.enums.StringPool;

/**
 * SourceTemplateConfig
 *
 * @author Force-oneself
 * @date 2022-04-30
 */
public interface SourceTemplateConfig extends TemplateConfig {

    /**
     * 模版路径默认实现
     *
     * @return return
     */
    @Override
    default String templatePath() {
        return this.templatePrefixPath() + this.templateFileName();
    }

    /**
     * 输出路径
     *
     * @return return
     */
    @Override
    default String outPath() {
        return this.outPrefixPath() + this.fileName() + this.fileNameSuffix() + this.fileSuffix();
    }

    /**
     * 输出路径前缀
     *
     * @return return
     */
    String outPrefixPath();


    /**
     * ignore
     *
     * @param outPath outPath
     */
    void setOutPath(String outPath);

    /**
     * 模版文件名
     *
     * @return return
     */
    String templateFileName();

    /**
     * 模版前缀路径
     *
     * @return return
     */
    String templatePrefixPath();

    /**
     * 输出文件名
     *
     * @return return
     */
    String fileName();

    /**
     * 输出文件名后缀
     *
     * @return return
     */
    default String fileNameSuffix() {
        return StringPool.EMPTY;
    }

    /**
     * 输出文件后缀
     *
     * @return return
     */
    default String fileSuffix() {
        return StringPool.JAVA_SUFFIX;
    }
}
