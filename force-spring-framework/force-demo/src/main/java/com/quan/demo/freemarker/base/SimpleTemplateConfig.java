package com.quan.demo.freemarker.base;

import com.quan.demo.freemarker.api.SourceTemplateConfig;
import com.quan.demo.freemarker.enums.StringPool;

/**
 * @author Force-oneself
 * @description TemplateConfig
 * @date 2022-03-17
 */
public class SimpleTemplateConfig implements SourceTemplateConfig {

    /**
     * 输出路径
     */
    protected String outPath;

    /**
     * source路径
     */
    protected String src = StringPool.MAVEN_SRC;

    /**
     * 输出路径前缀
     */
    protected String outPrefixPath = StringPool.EMPTY;


    /**
     * 模版路径前缀
     */
    protected String templatePrefixPath = StringPool.EMPTY;

    protected String templateFileName;

    protected String fileName;

    @Override
    public String outPrefixPath() {
        return this.outPrefixPath;
    }

    @Override
    public String templateFileName() {
        return this.templateFileName;
    }

    @Override
    public String templatePrefixPath() {
        return this.templatePrefixPath;
    }

    @Override
    public String fileName() {
        return this.fileName;
    }

    public String getOutPath() {
        return outPath;
    }

    @Override
    public void setOutPath(String outPath) {
        this.outPath = this.getOutPrefixPath() + this.getSrc() + outPath;
    }

    public String getOutPrefixPath() {
        return outPrefixPath;
    }

    public void setOutPrefixPath(String outPrefixPath) {
        this.outPrefixPath = outPrefixPath;
    }

    public String getTemplatePrefixPath() {
        return templatePrefixPath;
    }

    public void setTemplatePrefixPath(String templatePrefixPath) {
        this.templatePrefixPath = templatePrefixPath;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getTemplateFileName() {
        return templateFileName;
    }

    public void setTemplateFileName(String templateFileName) {
        this.templateFileName = templateFileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}

