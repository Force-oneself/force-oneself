package com.quan.tools.jackson;

import com.fasterxml.jackson.core.json.PackageVersion;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.quan.tools.util.DateTimeUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * JavaTimeModule
 *
 * @author Force-oneself
 * @date 2022-10-20
 */
public class JavaTimeModule extends SimpleModule {

    public static final JavaTimeModule INSTANCE = new JavaTimeModule();

    public JavaTimeModule() {
        super(PackageVersion.VERSION);
        this.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeUtils.DATETIME_FORMAT));
        this.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeUtils.DATE_FORMAT));
        this.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeUtils.TIME_FORMAT));
        this.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeUtils.DATETIME_FORMAT));
        this.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeUtils.DATE_FORMAT));
        this.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeUtils.TIME_FORMAT));
    }
}
