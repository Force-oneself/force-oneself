package com.quan.boot.mvc.jackson.auto;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;

import java.io.IOException;
import java.util.List;

/**
 * 自动填充
 *
 * @author Force-oneself
 * @date 2024-11-18
 */
public class AutoFillSerializer extends JsonSerializer<Object> implements ContextualSerializer {

    public AutoFillSerializer() {
        System.out.println("AutoFillSerializer");
    }

    private String type = "auto";

    @Override
    public void serialize(Object fillOrigin, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (fillOrigin == null) {
            return;
        }
        System.out.println("该字段名: " + jsonGenerator.getOutputContext().getCurrentName());
        if (fillOrigin instanceof Number) {
            // 填写原来的值
            jsonGenerator.writeNumber((Long) fillOrigin);
            // 填充自定义值
            jsonGenerator.writeFieldName("productVo");
            jsonGenerator.writeObject(new Demo.User());
        }

    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        if (beanProperty == null) {
            return serializerProvider.findNullValueSerializer(null);
        }
        AutoFill autofill = beanProperty.getAnnotation(AutoFill.class);
        if (autofill == null) {
            autofill = beanProperty.getContextAnnotation(AutoFill.class);
        }
        if (autofill == null) {
            return serializerProvider.findNullValueSerializer(null);
        }
        this.type = autofill.value();
        return this;
    }
}
