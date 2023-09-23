package com.quan.boot.mvc.desensitization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;

import java.io.IOException;
import java.util.Objects;

/**
 * DesensitizationSerializer
 *
 * @author Force-oneself
 * @date 2022-07-03
 */
public class DesensitizationSerializer extends StdScalarSerializer<String> {

    private final Masker masker;

    public DesensitizationSerializer(Masker masker) {
        super(String.class, false);
        this.masker = masker;
    }

    @Override
    public void serialize(String content, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (Objects.nonNull(masker)) {
            content = masker.mask(content);
        }
        gen.writeString(content);
    }

}
