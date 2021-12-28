package com.quan.demo.freemarker;

import java.io.Writer;

/**
 * @author Force-oneself
 * @description DataModel
 * @date 2021-12-26
 */
public interface DataHolder {

    /**
     * 数据模型
     *
     * @return java.lang.Object
     */
    Object dataModel();

    /**
     * 字节输出流
     *
     * @return java.io.Writer
     */
    Writer out();

    /**
     * 模版路径
     *
     * @return java.lang.String
     */
    String templatePath();
}
