package com.quan.demo.freemarker.api;

import java.util.List;
import java.util.Map;

/**
 * GroupDataModel
 *
 * @author Force-oneself
 * @date 2022-04-27
 */
public interface GroupDataModel extends DataModel {

    /**
     * 多分组模型
     *
     * @return 数据模型组集合
     */
    List<ClassMetaGroup> groups();

    /**
     * ignore
     *
     * @return return
     */
    @Override
    List<Map<String, Object>> dataModel();
}
