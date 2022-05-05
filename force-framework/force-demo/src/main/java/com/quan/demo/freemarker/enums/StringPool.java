package com.quan.demo.freemarker.enums;

/**
 * StringPool
 *
 * @author Force-oneself
 * @date 2022-05-05
 */
public interface StringPool {

    String EMPTY = "";

    /**
     * maven source文件夹
     */
    String MAVEN_SRC = "src/main/java/";

    /**
     * Java文件后缀
     */
    String JAVA_SUFFIX = ".java";

    /**
     * 默认编码格式
     */
    String DEFAULT_ENCODING = "UTF-8";
    /**
     * Service后缀
     */
    String SERVICE_SUFFIX = "Service";

    /**
     * ServiceImpl后缀
     */
    String SERVICE_IMPL_SUFFIX = "ServiceImpl";

    /**
     * Mapper后缀
     */
    String MAPPER_SUFFIX = "Mapper";

    /**
     * Controller后缀
     */
    String CONTROLLER_SUFFIX = "Controller";
}
