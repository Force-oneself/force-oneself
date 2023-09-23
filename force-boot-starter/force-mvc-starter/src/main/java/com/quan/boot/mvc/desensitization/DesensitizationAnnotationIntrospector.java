package com.quan.boot.mvc.desensitization;

import cn.hutool.core.util.DesensitizedUtil;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.NopAnnotationIntrospector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * DesensitizationAnnotationIntrospector
 *
 * @author Force-oneself
 * @date 2022-07-03
 */
public class DesensitizationAnnotationIntrospector extends NopAnnotationIntrospector {

    private final Map<String, Masker> operationMap;
    private final Map<String, Masker> innerOperationMap = initInnerOperations();

    public DesensitizationAnnotationIntrospector(List<Masker> maskers) {
        operationMap = maskers.stream()
                .collect(Collectors.toMap(operation -> operation.getClass().getName(), ops -> ops));
    }

    @Override
    public Object findSerializer(Annotated am) {
        Desensitization annotation = am.getAnnotation(Desensitization.class);
        if (annotation == null) {
            return null;
        }
        String type = annotation.type();
        return new DesensitizationSerializer(annotation.inner()
                ? innerOperationMap.get(type)
                : operationMap.get(type)
        );
    }

    private Map<String, Masker> initInnerOperations() {
        final Map<String, Masker> innerOperationMap = new HashMap<>(16);
        innerOperationMap.put(DesensitizationType.USER_ID, content -> DesensitizedUtil.desensitized(content, DesensitizedUtil.DesensitizedType.USER_ID));
        innerOperationMap.put(DesensitizationType.CHINESE_NAME, content -> DesensitizedUtil.desensitized(content, DesensitizedUtil.DesensitizedType.CHINESE_NAME));
        innerOperationMap.put(DesensitizationType.ID_CARD, content -> DesensitizedUtil.desensitized(content, DesensitizedUtil.DesensitizedType.ID_CARD));
        innerOperationMap.put(DesensitizationType.FIXED_PHONE, content -> DesensitizedUtil.desensitized(content, DesensitizedUtil.DesensitizedType.FIXED_PHONE));
        innerOperationMap.put(DesensitizationType.MOBILE_PHONE, content -> DesensitizedUtil.desensitized(content, DesensitizedUtil.DesensitizedType.MOBILE_PHONE));
        innerOperationMap.put(DesensitizationType.ADDRESS, content -> DesensitizedUtil.desensitized(content, DesensitizedUtil.DesensitizedType.ADDRESS));
        innerOperationMap.put(DesensitizationType.EMAIL, content -> DesensitizedUtil.desensitized(content, DesensitizedUtil.DesensitizedType.EMAIL));
        innerOperationMap.put(DesensitizationType.PASSWORD, content -> DesensitizedUtil.desensitized(content, DesensitizedUtil.DesensitizedType.PASSWORD));
        innerOperationMap.put(DesensitizationType.CAR_LICENSE, content -> DesensitizedUtil.desensitized(content, DesensitizedUtil.DesensitizedType.CAR_LICENSE));
        innerOperationMap.put(DesensitizationType.BANK_CARD, content -> DesensitizedUtil.desensitized(content, DesensitizedUtil.DesensitizedType.BANK_CARD));
        return innerOperationMap;
    }


}
