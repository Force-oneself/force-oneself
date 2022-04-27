package com.quan.demo.freemarker.api;

import java.util.List;

/**
 * GroupDataModel
 *
 * @author Force-oneself
 * @date 2022-04-27
 */
public interface GroupDataModel extends DataModel {

    List<ClassMetaGroup> groups();

    @Override
    List<Object> dataModel();
}
