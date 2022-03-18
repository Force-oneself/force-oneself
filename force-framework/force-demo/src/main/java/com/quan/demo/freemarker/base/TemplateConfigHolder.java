package com.quan.demo.freemarker.base;

/**
 * @author Force-oneself
 * @description TemplateConfig
 * @date 2022-03-17
 */
public class TemplateConfigHolder {

    /**
     * 输出路径
     */
    protected String outPath;

    /**
     * 模版路径
     */
    protected String templatePath;

    /**
     * 输出路径前缀
     */
    protected String outPrefixPath;

    /**
     * 模版路径前缀
     */
    protected String templatePrefixPath;

    /**
     * 编码格式
     */
    protected String encoding = "UTF-8";

    public String getOutPath() {
        return outPath;
    }

    public void setOutPath(String outPath) {
        this.outPath = outPath;
    }

    public String getTemplatePath() {
        return templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
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
}
