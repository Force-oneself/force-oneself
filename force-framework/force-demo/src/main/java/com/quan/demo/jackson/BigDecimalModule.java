package com.quan.demo.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

/**
 * BigDecimalModual
 *
 * @author Force-oneself
 * @date 2023-01-31
 */
public class BigDecimalModule extends SimpleModule {

    public BigDecimalModule() {
        addSerializer(BigDecimal.class, new BigDecimalSerializer());
        addDeserializer(BigDecimal.class, new BigDecimalDeserializer());
    }
}
