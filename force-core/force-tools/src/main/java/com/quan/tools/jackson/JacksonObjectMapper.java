package com.quan.tools.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.quan.tools.util.DateUtils;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Locale;
import java.util.TimeZone;

/**
 * JacksonObjectMapper
 *
 * @author Force-oneself
 * @date 2022-10-20
 */
public class JacksonObjectMapper extends ObjectMapper {

    private static final long serialVersionUID = 4288193147502386170L;

    private static final Locale CHINA = Locale.CHINA;

    public JacksonObjectMapper(ObjectMapper src) {
        super(src);
    }

    public JacksonObjectMapper() {
        super();
        //设置地点为中国
        super.setLocale(CHINA);
        //去掉默认的时间戳格式
        super.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        //设置为中国上海时区
        super.setTimeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));
        //序列化时，日期的统一格式
        super.setDateFormat(new SimpleDateFormat(DateUtils.PATTERN_DATETIME, Locale.CHINA));
        // 单引号
        super.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        // 允许JSON字符串包含非引号控制字符（值小于32的ASCII字符，包含制表符和换行符）
        super.configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true);
        super.configure(JsonReadFeature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER.mappedFeature(), true);
        //失败处理
        super.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        super.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //单引号处理
        super.configure(JsonReadFeature.ALLOW_SINGLE_QUOTES.mappedFeature(), true);
        //日期格式化
        super.registerModule(JavaTimeModule.INSTANCE);
        super.findAndRegisterModules();
    }

    @Override
    public ObjectMapper copy() {
        return new JacksonObjectMapper(this);
    }
}
