package com.quan.demo.freemarker.api;

import java.util.List;

/**
 * ClassMetaGroup 一次代码生成元数据信息最小单元接口
 *
 * @author Force-oneself
 * @date 2022-04-27
 */
public interface ClassMetaGroup {

    /**
     * 元信息组
     *
     * @return return
     */
    List<ClassMetaDefinition> metas();
}
