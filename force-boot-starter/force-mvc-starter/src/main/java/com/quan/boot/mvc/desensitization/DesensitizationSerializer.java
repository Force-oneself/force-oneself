package com.quan.boot.mvc.desensitization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Objects;

/**
 * DesensitizationSerializer
 *
 * @author Force-oneself
 * @date 2022-07-03
 */
public class DesensitizationSerializer extends StdScalarSerializer<Object> {

    private final Operation operation;

    public DesensitizationSerializer() {
        super(String.class, false);
        this.operation = null;
    }

    public DesensitizationSerializer(Operation operation) {
        super(String.class, false);
        this.operation = operation;
    }


    @Override
    public boolean isEmpty(SerializerProvider prov, Object value) {
        String str = (String) value;
        return str.isEmpty();
    }

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        String content = (String) value;
        if (Objects.nonNull(operation)) {
            content = operation.mask(content, null);
        }
        gen.writeString(content);
    }

    @Override
    public final void serializeWithType(Object value, JsonGenerator gen, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
        this.serialize(value, gen, provider);
    }

    @Override
    public JsonNode getSchema(SerializerProvider provider, Type typeHint) {
        return this.createSchemaNode("string", true);
    }

}
