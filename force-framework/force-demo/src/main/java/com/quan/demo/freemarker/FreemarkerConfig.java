package com.quan.demo.freemarker;

/**
 * FreemarkerConfig
 *
 * @author Force-oneself
 * @date 2022-04-29
 */
public class FreemarkerConfig {

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

    protected String templateFileName;

    private String fileName;

    private String fileNameSuffix = ".java";

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

    public String getFileNameSuffix() {
        return fileNameSuffix;
    }

    public void setFileNameSuffix(String fileNameSuffix) {
        this.fileNameSuffix = fileNameSuffix;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }
}
