package com.quan.boot.mvc.jackson;

import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.NopAnnotationIntrospector;

/**
 * DesensitizationAnnotationIntrospector
 *
 * @author Force-oneself
 * @date 2022-07-03
 */
public class BigDecimalFormatAnnotationIntrospector extends NopAnnotationIntrospector {

    @Override
    public Object findSerializer(Annotated am) {
        BigDecimalFormat annotation = am.getAnnotation(BigDecimalFormat.class);
        if (annotation == null) {
            return null;
        }
        BigDecimalSerializer bigDecimalSerializer = new BigDecimalSerializer();
        bigDecimalSerializer.setFormat(annotation.value());
        return bigDecimalSerializer;
    }
}
