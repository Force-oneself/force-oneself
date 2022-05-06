package com.quan.demo.freemarker.base;

import com.quan.demo.freemarker.api.TemplateConfig;
import com.quan.demo.freemarker.enums.StringPool;

/**
 * @author Force-oneself
 * @description TemplateConfig
 * @date 2022-03-17
 */
public class DefaultTemplateConfig implements TemplateConfig {

    /**
     * 模版文件信息
     */
    protected String templateFileName;
    protected String templateFileNameSuffix = StringPool.EMPTY;
    protected String templateFileSuffix = StringPool.FTL_SUFFIX;
    protected String templatePrefixPath = StringPool.EMPTY;

    /**
     * 输出文件信息
     */
    protected String outPrefixPath = StringPool.EMPTY;
    protected String fileName;
    protected String fileNameSuffix = StringPool.EMPTY;
    protected String fileSuffix = StringPool.JAVA_SUFFIX;
    protected String encoding = StringPool.DEFAULT_ENCODING;

    @Override
    public String templatePath() {
        return this.templatePrefixPath
                .concat(this.templateFileName)
                .concat(this.templateFileNameSuffix)
                .concat(this.templateFileSuffix);
    }

    @Override
    public String outPath() {
        return this.outPrefixPath
                .concat(this.fileName)
                .concat(this.fileNameSuffix)
                .concat(this.fileSuffix);
    }

    @Override
    public String encoding() {
        return this.encoding;
    }

    public String getTemplateFileName() {
        return templateFileName;
    }

    public void setTemplateFileName(String templateFileName) {
        this.templateFileName = templateFileName;
    }

    public String getTemplatePrefixPath() {
        return templatePrefixPath;
    }

    public void setTemplatePrefixPath(String templatePrefixPath) {
        this.templatePrefixPath = templatePrefixPath;
    }

    public String getOutPrefixPath() {
        return outPrefixPath;
    }

    public void setOutPrefixPath(String outPrefixPath) {
        this.outPrefixPath = outPrefixPath;
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

    public String getFileSuffix() {
        return fileSuffix;
    }

    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getTemplateFileNameSuffix() {
        return templateFileNameSuffix;
    }

    public void setTemplateFileNameSuffix(String templateFileNameSuffix) {
        this.templateFileNameSuffix = templateFileNameSuffix;
    }

    public String getTemplateFileSuffix() {
        return templateFileSuffix;
    }

    public void setTemplateFileSuffix(String templateFileSuffix) {
        this.templateFileSuffix = templateFileSuffix;
    }
}

