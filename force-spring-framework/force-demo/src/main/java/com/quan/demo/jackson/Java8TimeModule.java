package com.quan.demo.jackson;

import com.fasterxml.jackson.core.json.PackageVersion;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.quan.common.util.DateUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * JavaTimeModule
 *
 * @author Force-oneself
 * @date 2022-10-20
 */
public class Java8TimeModule extends SimpleModule {

    public static final Java8TimeModule INSTANCE = new Java8TimeModule();
    public static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ofPattern(DateUtils.PATTERN_DATETIME);
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern(DateUtils.PATTERN_DATE);
    public static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern(DateUtils.PATTERN_TIME);

    public Java8TimeModule() {
        super(PackageVersion.VERSION);
        this.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DATETIME_FORMAT));
        this.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DATETIME_FORMAT));
        this.addDeserializer(LocalDate.class, new LocalDateDeserializer(DATE_FORMAT));
        this.addSerializer(LocalDate.class, new LocalDateSerializer(DATE_FORMAT));
        this.addDeserializer(LocalTime.class, new LocalTimeDeserializer(TIME_FORMAT));
        this.addSerializer(LocalTime.class, new LocalTimeSerializer(TIME_FORMAT));
    }
}
