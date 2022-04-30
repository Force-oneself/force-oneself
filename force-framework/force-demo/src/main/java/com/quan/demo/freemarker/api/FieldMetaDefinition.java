package com.quan.demo.freemarker.api;

/**
 * FieldMeta 成员变量元信息接口
 *
 * @author Force-oneself
 * @date 2022-04-27
 */
public interface FieldMetaDefinition extends MetaDefinition {

    /**
     * 默认名称为类首字母小写
     *
     * @return 字段名
     */
    default String getName() {
        String type = getType();
        char[] chars = type.toCharArray();
        // 首字母小写方法，大写会变成小写
        if (Character.isUpperCase(chars[0])) {
            chars[0] += 32;
        }
        return String.valueOf(chars);
    }
}
