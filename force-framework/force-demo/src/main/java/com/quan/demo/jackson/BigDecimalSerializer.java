package com.quan.demo.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * BigDecimalSerializer
 *
 * @author Force-oneself
 * @date 2023-01-31
 */
public class BigDecimalSerializer extends JsonSerializer<BigDecimal> implements ContextualSerializer {

    public final static String FORMAT_PATTERN = "#0.00";
    private String format = FORMAT_PATTERN;

    private final static BigDecimalSerializer INSTANCE = new BigDecimalSerializer();
    private final static Map<String, DecimalFormat> DECIMAL_FORMAT_CACHE = new ConcurrentHashMap<>(16);


    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws
            JsonMappingException {
        if (beanProperty == null) {
            return serializerProvider.findNullValueSerializer(null);
        }
        if (Objects.equals(beanProperty.getType().getRawClass(), BigDecimal.class)) {
            BigDecimalFormat bigDecimalFormat = beanProperty.getAnnotation((BigDecimalFormat.class));
            if (bigDecimalFormat == null) {
                bigDecimalFormat = beanProperty.getContextAnnotation(BigDecimalFormat.class);
            }
            if (bigDecimalFormat == null) {
                return INSTANCE;
            }
            BigDecimalSerializer bigDecimalSerializer = new BigDecimalSerializer();
            bigDecimalSerializer.setFormat(bigDecimalFormat.value());
            return bigDecimalSerializer;
        }
        return serializerProvider.findValueSerializer(beanProperty.getType(), beanProperty);

    }

    @Override
    public void serialize(BigDecimal bigDecimal, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeString(DECIMAL_FORMAT_CACHE.computeIfAbsent(format, DecimalFormat::new).format(bigDecimal));
    }

    public void setFormat(String format) {
        this.format = format;
    }
}