package com.quan.common.util.enums;

import com.quan.common.util.DateAccuracy;
import com.quan.common.util.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.TimeZone;
import java.util.WeakHashMap;

/**
 * 日期操作的工具类
 * 所有的日期操作相关的工具方法都集中定义在这里。
 * <p>
 * 一个星期以星期一作为开始
 * <p>
 * 工具类使用JDK1.8 java.time包下API实现
 * 仅提供一个与Date适配方法
 * <p>
 *
 * @author hey
 * <p>
 * created by hey
 * Date: 2019/12/15
 */
public final class DatetimeUtils {

    private static final Logger logger = LoggerFactory.getLogger(DatetimeUtils.class);

    public static final String FORMAT_YEAR = "yyyy";
    public static final String FORMAT_MONTH = "MM";
    public static final String FORMAT_DAY = "dd";
    public static final String FORMAT_HOUR = "HH";
    public static final String FORMAT_MINUTE = "mm";
    public static final String FORMAT_SECOND = "ss";
    public static final String FORMAT_MILLISECOND = "SSS";

    public static final String FORMAT_MD = "MM-dd";
    public static final String FORMAT_HM = "HH:mm";
    public static final String FORMAT_YM = "yyyy-MM";
    public static final String FORMAT_HMS = "HH:mm:ss";
    public static final String FORMAT_YMD = "yyyy-MM-dd";
    public static final String FORMAT_MDHM = "MM-dd HH:mm";
    public static final String FORMAT_YMDH = "yyyy-MM-dd HH";
    public static final String FORMAT_YMDHM = "yyyy-MM-dd HH:mm";
    public static final String FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_YMDHMSS = "yyyy-MM-dd HH:mm:ss.S";
    public static final String FORMAT_YMDHMSSSS = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String FORMAT_YMDHMSSSS_CST = "yyyy-MM-dd'T'HH:mm:ss.SSS+0800";
    public static final String FORMAT_YMDHMSSSS_CST_TIME_ZONE = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    public static final String FORMAT_YM_SN = "yyyyMM";
    public static final String FORMAT_YMD_SN = "yyyyMMdd";
    public static final String FORMAT_YMDH_SN = "yyyyMMddHH";
    public static final String FORMAT_YMDHM_SN = "yyyyMMddHHmm";
    public static final String FORMAT_YMDHMS_SN = "yyyyMMddHHmmss";
    public static final String FORMAT_YMDHMSS_SN = "yyyyMMddHHmmssS";
    public static final String FORMAT_YMDHMSSSS_SN = "yyyyMMddHHmmssSSS";

    public static final ZoneId SYSTEM_DEFAULT_ZONE_ID = ZoneId.systemDefault();
    public static final DateTimeFormatter DEFAULT_DATETIME_FORMATTER = DateTimeFormatter.ofPattern(FORMAT_YMDHMS);
    public static final DateTimeFormatter DEFAULT_DATE_FORMATTER = DateTimeFormatter.ofPattern(FORMAT_YMD);
    public static final DateTimeFormatter DEFAULT_TIME_FORMATTER = DateTimeFormatter.ofPattern(FORMAT_HMS);

    private static final ThreadLocal<Map<String, SimpleDateFormat>> FORMAT_THREAD_LOCAL = ThreadLocal.withInitial(WeakHashMap::new);

    /**
     * 获取当前日期LocalDateTime
     *
     * @return 当前LocalDateTime
     */
    public static LocalDateTime getCurrentLocalDateTime() {
        return LocalDateTime.now();
    }

    /**
     * 获取当前日期LocalDate
     *
     * @return 当前LocalDate
     */
    public static LocalDate getCurrentLocalDate() {
        return LocalDate.now();
    }

    /**
     * 获取当前时间LocalTime
     *
     * @return 当前LocalTime
     */
    public static LocalTime getCurrentLocalTime() {
        return LocalTime.now();
    }

    /**
     * 获取当前日期时间Instant
     *
     * @return 当前Instant
     */
    public static Instant getCurrentInstant() {
        return Instant.now();
    }

    /**
     * 获取当前日期时间Date
     *
     * @return 当前Date
     */
    public static Date getCurrentDate() {
        return new Date();
    }

    /**
     * 获取到指定时间精度为止的当前日期时间Date
     *
     * @param DateAccuracy 指定时间精度
     * @return 当前Date
     */
    public static Date getCurrentDate(DateAccuracy DateAccuracy) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        int millisecond = calendar.get(Calendar.MILLISECOND);
        calendar.setTimeInMillis(-62167420800000L);
        switch (DateAccuracy) {
            case MILLISECOND: {
                calendar.set(Calendar.MILLISECOND, millisecond);
            }
            case SECOND: {
                calendar.set(Calendar.SECOND, second);
            }
            case MINUTE: {
                calendar.set(Calendar.MINUTE, minute);
            }
            case HOUR: {
                calendar.set(Calendar.HOUR_OF_DAY, hour);
            }
            case DAY: {
                calendar.set(Calendar.DAY_OF_MONTH, day);
            }
            case MONTH: {
                calendar.set(Calendar.MONTH, month);
            }
            case YEAR: {
                calendar.set(Calendar.YEAR, year);
            }
        }
        return calendar.getTime();
    }

    /**
     * 获取当前时间字符串 字符串格式:yyyy-MM-dd HH:mm:ss
     *
     * @return yyyy-MM-dd HH:mm:ss格式当前时间字符串
     */
    public static String getCurrentTimeString() {
        return toString(new Date(), FORMAT_YMDHMS);
    }

    /**
     * 获取指定格式的当前时间字符串
     *
     * @param format 指定时间字符串格式
     * @return 指定格式的当前时间字符串
     */
    public static String getCurrentTimeString(String format) {
        return toString(new Date(), format);
    }

    /**
     * 转换为LocalDateTime
     *
     * @param localDate 指定日期
     * @return 按指定LocalDate和当前Time转换为LocalDateTime
     */
    public static LocalDateTime toLocalDateTime(LocalDate localDate) {
        return toLocalDateTime(localDate, LocalTime.now());
    }

    /**
     * 转换为LocalDateTime
     *
     * @param localTime 指定时间
     * @return 按当前Date和指定LocalTime转换为LocalDateTime
     */
    public static LocalDateTime toLocalDateTime(LocalTime localTime) {
        return toLocalDateTime(LocalDate.now(), localTime);
    }

    /**
     * 转换为LocalDateTime
     *
     * @param localDate 指定日期
     * @param localTime 指定时间
     * @return 按指定LocalDate和指定LocalTime转换为LocalDateTIme
     */
    public static LocalDateTime toLocalDateTime(LocalDate localDate, LocalTime localTime) {
        return Objects.nonNull(localDate) && Objects.nonNull(localTime) ? localDate.atTime(localTime) : null;
    }

    /**
     * 转换为LocalDateTime
     *
     * @param date 指定日期时间
     * @return 按指定Date转换为LocalDateTIme
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        return Objects.nonNull(date) ? LocalDateTime.ofInstant(date.toInstant(), SYSTEM_DEFAULT_ZONE_ID) : null;
    }

    /**
     * 转换为LocalDateTime
     *
     * @param timeString 指定时间字符串
     * @return 按指定时间字符串及yyyy-MM-dd HH:mm:ss格式转换为LocalDateTIme
     */
    public static LocalDateTime toLocalDateTime(String timeString) {
        return toLocalDateTime(timeString, DEFAULT_DATETIME_FORMATTER);
    }

    /**
     * 转换为LocalDateTime
     *
     * @param timeString 指定时间字符串
     * @param format     指定时间字符串格式
     * @return 按指定时间字符串及指定格式转换为LocalDateTIme
     */
    public static LocalDateTime toLocalDateTime(String timeString, String format) {
        return StringUtils.isBlank(timeString) || StringUtils.isBlank(format) ? null
                : toLocalDateTime(timeString, DateTimeFormatter.ofPattern(format));
    }

    /**
     * 转换为LocalDateTime
     *
     * @param timeString 指定时间字符串
     * @param formatter  时间字符串格式化对象
     * @return 按指定时间字符串及指定格式化对象转换为LocalDateTIme
     */
    public static LocalDateTime toLocalDateTime(String timeString, DateTimeFormatter formatter) {
        return StringUtils.isBlank(timeString) || Objects.isNull(formatter) ? null
                : LocalDateTime.parse(timeString, formatter);
    }

    /**
     * 转换为LocalDate
     *
     * @param localDateTime 指定日期时间
     * @return 按指定LocalDateTime转换为LocalDate
     */
    public static LocalDate toLocalDate(LocalDateTime localDateTime) {
        return Objects.isNull(localDateTime) ? null
                : localDateTime.toLocalDate();
    }

    /**
     * 转换为LocalDate
     *
     * @param date 指定日期
     * @return 按指定Date转换为LocalDate
     */
    public static LocalDate toLocalDate(Date date) {
        if (Objects.isNull(date)) {
            return null;
        }
        LocalDateTime localDateTime = toLocalDateTime(date);
        if (Objects.isNull(localDateTime)) {
            return null;
        }
        return localDateTime.toLocalDate();
    }

    /**
     * 转换为LocalDate
     *
     * @param timeString 指定时间字符串
     * @param format     指定时间字符串格式
     * @return 按指定时间字符串及时间字符串格式转换为LocalDate
     */
    public static LocalDate toLocalDate(String timeString, String format) {
        return StringUtils.isBlank(timeString) || StringUtils.isBlank(format) ? null
                : toLocalDate(timeString, DateTimeFormatter.ofPattern(format));
    }

    /**
     * 转换为LocalDate
     *
     * @param timeString 指定时间字符串
     * @param formatter  指定时间字符串格式化对象
     * @return 按指定时间字符串及时间字符串格式化对象转换为LocalDate
     */
    public static LocalDate toLocalDate(String timeString, DateTimeFormatter formatter) {
        return StringUtils.isBlank(timeString) || Objects.isNull(formatter) ? null
                : LocalDate.parse(timeString, formatter);
    }

    /**
     * 转换为LocalTime
     *
     * @param localDateTime 指定日期时间
     * @return 按指定LocalDateTime转换为LocalTime
     */
    public static LocalTime toLocalTime(LocalDateTime localDateTime) {
        return ObjectUtils.defaultIfNull(localDateTime, DatetimeUtils::getCurrentLocalDateTime).toLocalTime();
    }

    /**
     * 转换为LocalTime
     *
     * @param date 指定日期
     * @return 按指定Date转换为LocalTime
     */
    public static LocalTime toLocalTime(Date date) {
        if (Objects.isNull(date)) {
            return null;
        }
        LocalDateTime localDateTime = toLocalDateTime(date);
        if (Objects.isNull(localDateTime)) {
            return null;
        }
        return localDateTime.toLocalTime();
    }

    /**
     * 转换为LocalTime
     *
     * @param timeString 指定时间字符串
     * @param format     指定时间字符串格式
     * @return 按指定时间字符串及时间字符串格式转换为LocalTime
     */
    public static LocalTime toLocalTime(String timeString, String format) {
        return StringUtils.isBlank(timeString) || StringUtils.isBlank(format) ? null
                : toLocalTime(timeString, DateTimeFormatter.ofPattern(format));
    }

    /**
     * 转换为LocalTime
     *
     * @param timeString 指定时间字符串
     * @param formatter  指定时间字符串格式化对象
     * @return 按指定时间字符串及时间字符串格式化对象转换为LocalTime
     */
    public static LocalTime toLocalTime(String timeString, DateTimeFormatter formatter) {
        return StringUtils.isBlank(timeString) || Objects.isNull(formatter) ? null
                : LocalTime.parse(timeString, formatter);
    }

    /**
     * 转换为Date
     *
     * @param localDateTime 指定日期时间
     * @return 按指定LocalDateTime转换为Date
     */
    public static Date toDate(LocalDateTime localDateTime) {
        if (Objects.isNull(localDateTime)) {
            return null;
        }
        return Date.from(localDateTime.atZone(SYSTEM_DEFAULT_ZONE_ID).toInstant());
    }

    /**
     * 转换为Date
     *
     * @param localDate 指定日期
     * @return 按指定LocalDate转换为Date
     */
    public static Date toDate(LocalDate localDate) {
        return Objects.isNull(localDate) ? null : toDate(localDate.atStartOfDay());
    }

    /**
     * 转换为Date
     *
     * @param localTime 指定时间
     * @return 按指定LocalTime转换为Date
     */
    public static Date toDate(LocalTime localTime) {
        if (Objects.isNull(localTime)) {
            return null;
        }
        return toDate(LocalDateTime.of(LocalDate.now(), localTime));
    }

    /**
     * 转换为Date
     *
     * @param timeString 指定时间字符串
     * @return 按指定时间字符串及时间字符串格式转换为Date
     */
    public static Date toDate(String timeString) {
        return toDate(timeString, FORMAT_YMDHMS);
    }

    /**
     * 转换为Date
     *
     * @param timeString 指定时间字符串
     * @param timeZone   指定时区
     * @return 按指定时间字符串及时间字符串格式转换为Date
     */
    public static Date toDate(String timeString, TimeZone timeZone) {
        return toDate(timeString, FORMAT_YMDHMS, timeZone);
    }

    /**
     * 转换为Date
     *
     * @param timeString 指定时间字符串
     * @param format     指定时间字符串格式
     * @return 按指定时间字符串及时间字符串格式化对象转换为Date
     */
    public static Date toDate(String timeString, String format) {
        if (StringUtils.isBlank(timeString) || StringUtils.isBlank(format)) {
            return null;
        }
        try {
            return getSimpleDateFormat(format).parse(timeString);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 转换为Date
     *
     * @param timeString 指定时间字符串
     * @param format     指定时间字符串格式
     * @param timeZone   指定时区
     * @return 按指定时间字符串及时间字符串格式化对象转换为Date
     */
    public static Date toDate(String timeString, String format, TimeZone timeZone) {
        if (StringUtils.isBlank(timeString) || StringUtils.isBlank(format)) {
            return null;
        }
        try {
            return getSimpleDateFormat(format, ObjectUtils.defaultIfNull(timeZone, TimeZone.getDefault()))
                    .parse(timeString);
        } catch (ParseException e) {
            return null;
        }
    }


    /**
     * 转换为时间字符串
     *
     * @param localDateTime 指定日期时间
     * @return 按指定日期时间转换为时间字符串
     */
    public static String toString(LocalDateTime localDateTime) {
        return toString(localDateTime, DEFAULT_DATETIME_FORMATTER);
    }

    /**
     * 转换为时间字符串
     *
     * @param localDateTime 指定日期时间
     * @param format        指定格式
     * @return 按指定日期时间转换为指定格式时间字符串
     */
    public static String toString(LocalDateTime localDateTime, String format) {
        return Objects.isNull(localDateTime) || StringUtils.isBlank(format) ? ""
                : toString(localDateTime, DateTimeFormatter.ofPattern(format));
    }

    /**
     * 转换为时间字符串
     *
     * @param localDateTime 指定日期时间
     * @param formatter     指定格式化对象
     * @return 按指定日期时间转换为指定格式时间字符串
     */
    public static String toString(LocalDateTime localDateTime, DateTimeFormatter formatter) {
        return Objects.isNull(localDateTime) || Objects.isNull(formatter) ? ""
                : formatter.format(localDateTime);
    }

    /**
     * 转换为时间字符串
     *
     * @param localDate 指定日期
     * @return 按指定日期转换为指定格式时间字符串
     */
    public static String toString(LocalDate localDate) {
        return toString(localDate, DEFAULT_DATE_FORMATTER);
    }

    /**
     * 转换为时间字符串
     *
     * @param localDate 指定日期
     * @param format    指定格式
     * @return 按指定日期转换为指定格式时间字符串
     */
    public static String toString(LocalDate localDate, String format) {
        return Objects.isNull(localDate) || StringUtils.isBlank(format) ? ""
                : toString(localDate, DateTimeFormatter.ofPattern(format));
    }

    /**
     * 转换为时间字符串
     *
     * @param localDate 指定日期
     * @param formatter 指定格式化对象
     * @return 按指定日期转换为指定格式时间字符串
     */
    public static String toString(LocalDate localDate, DateTimeFormatter formatter) {
        return Objects.isNull(localDate) || Objects.isNull(formatter) ? ""
                : formatter.format(localDate);
    }

    /**
     * 转换为时间字符串
     *
     * @param localTime 指定时间
     * @return 按指定时间转换为指定格式时间字符串
     */
    public static String toString(LocalTime localTime) {
        return toString(localTime, DEFAULT_TIME_FORMATTER);
    }

    /**
     * 转换为时间字符串
     *
     * @param localTime 指定时间
     * @param format    指定格式
     * @return 按指定时间转换为指定格式时间字符串
     */
    public static String toString(LocalTime localTime, String format) {
        return Objects.isNull(localTime) || StringUtils.isBlank(format) ? ""
                : toString(localTime, DateTimeFormatter.ofPattern(format));
    }

    /**
     * 转换为时间字符串
     *
     * @param localTime 指定时间
     * @param formatter 指定格式化对象
     * @return 按指定时间转换为指定格式时间字符串
     */
    public static String toString(LocalTime localTime, DateTimeFormatter formatter) {
        return Objects.isNull(localTime) || Objects.isNull(formatter) ? ""
                : formatter.format(localTime);
    }

    /**
     * 转换为时间字符串
     *
     * @param date 指定日期时间
     * @return 按指定日期时间转换为指定格式时间字符串
     */
    public static String toString(Date date) {
        return toString(date, FORMAT_YMDHMS);
    }

    /**
     * 转换为时间字符串
     *
     * @param date   指定日期时间
     * @param format 指定格式
     * @return 按指定时间转换为指定格式时间字符串
     */
    public static String toString(Date date, String format) {
        return Objects.isNull(date) || StringUtils.isBlank(format) ? ""
                : toString(date, getSimpleDateFormat(format));
    }

    /**
     * 转换为时间字符串
     *
     * @param date   指定日期时间
     * @param format 指定格式化对象
     * @return 按指定时间转换为指定格式时间字符串
     */
    public static String toString(Date date, SimpleDateFormat format) {
        return Objects.isNull(date) || Objects.isNull(format) ? ""
                : format.format(date);
    }

    /**
     * 转换日期字符串格式
     *
     * @param timeString 时间字符串
     * @param fromFormat 原时间字符串格式
     * @param toFormat   目标时间字符串格式
     * @return 转换后日期字符串
     */
    public static String castFormat(String timeString, String fromFormat, String toFormat) {
        return StringUtils.isBlank(timeString) || StringUtils.isBlank(fromFormat) || StringUtils.isBlank(toFormat) ? ""
                : toString(toDate(timeString, fromFormat), toFormat);
    }

    /**
     * 判断给定时间是否为当前时间之前
     *
     * @param localDateTime 给定日期时间
     * @return 给定时间是否早于当前时间
     */
    public static boolean isTimeBeforeNow(LocalDateTime localDateTime) {
        return isTimeBefore(localDateTime, LocalDateTime.now());
    }

    /**
     * 判断给定时间是否为当前时间之前
     *
     * @param date 给定日期时间
     * @return 给定时间是否早于当前时间
     */
    public static boolean isTimeBeforeNow(Date date) {
        return isTimeBefore(date, new Date());
    }

    /**
     * 判断基础时间是否在比较时间之前
     *
     * @param baseLocalDateTime    基础日期时间
     * @param compareLocalDateTime 比较日期时间
     * @return 基础日期时间是否早于比较日期时间
     */
    public static boolean isTimeBefore(LocalDateTime baseLocalDateTime, LocalDateTime compareLocalDateTime) {
        return !Objects.isNull(baseLocalDateTime) && !Objects.isNull(compareLocalDateTime)
                && baseLocalDateTime.isBefore(compareLocalDateTime);
    }

    /**
     * 判断基础时间是否在比较时间之前
     *
     * @param baseDate    基础日期时间
     * @param compareDate 比较日期时间
     * @return 基础日期时间是否早于比较日期时间
     */
    public static boolean isTimeBefore(Date baseDate, Date compareDate) {
        return !Objects.isNull(baseDate) && !Objects.isNull(compareDate) && baseDate.before(compareDate);
    }

    /**
     * 判断给定时间是否为当前时间之后
     *
     * @param localDateTime 给定日期时间
     * @return 给定时间是否晚于当前时间
     */
    public static boolean isTimeAfterNow(LocalDateTime localDateTime) {
        return isTimeAfter(localDateTime, LocalDateTime.now());
    }

    /**
     * 判断给定时间是否为当前时间之后
     *
     * @param date 给定日期时间
     * @return 给定时间是否晚于当前时间
     */
    public static boolean isTimeAfterNow(Date date) {
        return isTimeAfter(date, new Date());
    }

    /**
     * 判断基础时间是否在比较时间之后
     *
     * @param baseLocalDateTime    基础日期时间
     * @param compareLocalDateTime 比较日期时间
     * @return 基础日期时间是否晚于比较日期时间
     */
    public static boolean isTimeAfter(LocalDateTime baseLocalDateTime, LocalDateTime compareLocalDateTime) {
        return !Objects.isNull(baseLocalDateTime) && !Objects.isNull(compareLocalDateTime)
                && baseLocalDateTime.isAfter(compareLocalDateTime);
    }

    /**
     * 判断基础时间是否在比较时间之后
     *
     * @param baseDate    基础日期时间
     * @param compareDate 比较日期时间
     * @return 基础日期时间是否晚于比较日期时间
     */
    public static boolean isTimeAfter(Date baseDate, Date compareDate) {
        return !Objects.isNull(baseDate) && !Objects.isNull(compareDate) && baseDate.after(compareDate);
    }

    /**
     * 判断基础时间是否与比较时间相等
     *
     * @param baseLocalDateTime    基础日期时间
     * @param compareLocalDateTime 比较日期时间
     * @return 基础日期时间与比较日期时间一致
     */
    public static boolean isTimeEquals(LocalDateTime baseLocalDateTime, LocalDateTime compareLocalDateTime) {
        return !Objects.isNull(baseLocalDateTime) && !Objects.isNull(compareLocalDateTime)
                && baseLocalDateTime.isEqual(compareLocalDateTime);
    }

    /**
     * 判断基础时间是否与比较时间相等
     *
     * @param baseDate    基础日期时间
     * @param compareDate 比较日期时间
     * @return 基础日期时间与比较日期时间一致
     */
    public static boolean isTimeEquals(Date baseDate, Date compareDate) {
        return !Objects.isNull(baseDate) && !Objects.isNull(compareDate)
                && baseDate.equals(compareDate);
    }

    /**
     * 计算两个时间间隔
     * 参数为空则自动填充当前时间
     *
     * @param fromDatetime 开始时间
     * @param toDatetime   结束时间
     * @return 时间间隔天数
     */
    public static long timeBetweenInDays(LocalDateTime fromDatetime, LocalDateTime toDatetime) {
        return timeBetween(fromDatetime, toDatetime).toDays();
    }

    /**
     * 计算两个时间间隔
     * 参数为空则自动填充当前时间
     *
     * @param fromDatetime 开始时间
     * @param toDatetime   结束时间
     * @return 时间间隔小时
     */
    public static long timeBetweenInHours(LocalDateTime fromDatetime, LocalDateTime toDatetime) {
        return timeBetween(fromDatetime, toDatetime).toHours();
    }

    /**
     * 计算两个时间间隔
     * 参数为空则自动填充当前时间
     *
     * @param fromDatetime 开始时间
     * @param toDatetime   结束时间
     * @return 时间间隔分钟
     */
    public static long timeBetweenInMinutes(LocalDateTime fromDatetime, LocalDateTime toDatetime) {
        return timeBetween(fromDatetime, toDatetime).toMinutes();
    }

    /**
     * 计算两个时间间隔
     * 参数为空则自动填充当前时间
     *
     * @param fromDatetime 开始时间
     * @param toDatetime   结束时间
     * @return 时间间隔毫秒
     */
    public static long timeBetweenInMillis(LocalDateTime fromDatetime, LocalDateTime toDatetime) {
        return timeBetween(fromDatetime, toDatetime).toMillis();
    }

    /**
     * 计算两个时间间隔
     * 参数为空则自动填充当前时间
     *
     * @param fromDatetime 开始时间
     * @param toDatetime   结束时间
     * @return 时间间隔微妙
     */
    public static long timeBetweenInNanos(LocalDateTime fromDatetime, LocalDateTime toDatetime) {
        return timeBetween(fromDatetime, toDatetime).toNanos();
    }

    /**
     * 计算两个时间间隔
     * 参数为空则自动填充当前时间
     *
     * @param fromDatetime 开始时间
     * @param toDatetime   结束时间
     * @return 时间间隔对象
     */
    public static Duration timeBetween(LocalDateTime fromDatetime, LocalDateTime toDatetime) {
        return Duration.between(
                ObjectUtils.defaultIfNull(fromDatetime, DatetimeUtils::getCurrentLocalDateTime),
                ObjectUtils.defaultIfNull(toDatetime, DatetimeUtils::getCurrentLocalDateTime)
        );
    }

    /**
     * 时间加
     *
     * @param date         基础时间
     * @param dateAccuracy 计算时间精度
     * @param amountToAdd  时间加数值
     * @return 计算后日期时间
     */
    public static Date plus(Date date, DateAccuracy dateAccuracy, int amountToAdd) {
        if (Objects.isNull(date) || Objects.isNull(dateAccuracy)) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (amountToAdd == 0) {
            return calendar.getTime();
        }
        calendar.add(selectField(dateAccuracy), amountToAdd);
        return calendar.getTime();
    }

    /**
     * 时间加N年
     *
     * @param date        基础时间
     * @param amountToAdd 时间加数值
     * @return 计算后日期时间
     */
    public static Date plusYears(Date date, int amountToAdd) {
        return plus(date, DateAccuracy.YEAR, amountToAdd);
    }

    /**
     * 时间加N月
     *
     * @param date        基础时间
     * @param amountToAdd 时间加数值
     * @return 计算后日期时间
     */
    public static Date plusMonths(Date date, int amountToAdd) {
        return plus(date, DateAccuracy.MONTH, amountToAdd);
    }

    /**
     * 时间加N天
     *
     * @param date        基础时间
     * @param amountToAdd 时间加数值
     * @return 计算后日期时间
     */
    public static Date plusDays(Date date, int amountToAdd) {
        return plus(date, DateAccuracy.DAY, amountToAdd);
    }

    /**
     * 时间加N小时
     *
     * @param date        基础时间
     * @param amountToAdd 时间加数值
     * @return 计算后日期时间
     */
    public static Date plusHours(Date date, int amountToAdd) {
        return plus(date, DateAccuracy.HOUR, amountToAdd);
    }

    /**
     * 时间加N分
     *
     * @param date        基础时间
     * @param amountToAdd 时间加数值
     * @return 计算后日期时间
     */
    public static Date plusMinutes(Date date, int amountToAdd) {
        return plus(date, DateAccuracy.MINUTE, amountToAdd);
    }

    /**
     * 时间加N秒
     *
     * @param date        基础时间
     * @param amountToAdd 时间加数值
     * @return 计算后日期时间
     */
    public static Date plusSeconds(Date date, int amountToAdd) {
        return plus(date, DateAccuracy.SECOND, amountToAdd);
    }

    /**
     * 时间加N周
     *
     * @param date        基础时间
     * @param amountToAdd 时间加数值
     * @return 计算后日期时间
     */
    public static Date plusWeeks(Date date, int amountToAdd) {
        return plus(date, DateAccuracy.DAY, 7 * amountToAdd);
    }

    /**
     * 时间减
     *
     * @param date             基础时间
     * @param dateAccuracy     计算时间精度
     * @param amountToSubtract 时间减数值
     * @return 计算后日期时间
     */
    public static Date minus(Date date, DateAccuracy dateAccuracy, int amountToSubtract) {
        return plus(date, dateAccuracy, -1 * amountToSubtract);
    }

    /**
     * 时间减N年
     *
     * @param date             基础时间
     * @param amountToSubtract 时间减数值
     * @return 计算后日期时间
     */
    public static Date minusYears(Date date, int amountToSubtract) {
        return minus(date, DateAccuracy.YEAR, amountToSubtract);
    }

    /**
     * 时间减N月
     *
     * @param date             基础时间
     * @param amountToSubtract 时间减数值
     * @return 计算后日期时间
     */
    public static Date minusMonths(Date date, int amountToSubtract) {
        return minus(date, DateAccuracy.MONTH, amountToSubtract);
    }

    /**
     * 时间减N日
     *
     * @param date             基础时间
     * @param amountToSubtract 时间减数值
     * @return 计算后日期时间
     */
    public static Date minusDays(Date date, int amountToSubtract) {
        return minus(date, DateAccuracy.DAY, amountToSubtract);
    }

    /**
     * 时间减N小时
     *
     * @param date             基础时间
     * @param amountToSubtract 时间减数值
     * @return 计算后日期时间
     */
    public static Date minusHours(Date date, int amountToSubtract) {
        return minus(date, DateAccuracy.HOUR, amountToSubtract);
    }

    /**
     * 时间减N分
     *
     * @param date             基础时间
     * @param amountToSubtract 时间减数值
     * @return 计算后日期时间
     */
    public static Date minusMinutes(Date date, int amountToSubtract) {
        return minus(date, DateAccuracy.MINUTE, amountToSubtract);
    }

    /**
     * 时间减N秒
     *
     * @param date             基础时间
     * @param amountToSubtract 时间减数值
     * @return 计算后日期时间
     */
    public static Date minusSeconds(Date date, int amountToSubtract) {
        return minus(date, DateAccuracy.SECOND, amountToSubtract);
    }

    /**
     * 时间减N周
     *
     * @param date             基础时间
     * @param amountToSubtract 时间减数值
     * @return 计算后日期时间
     */
    public static Date minusWeeks(Date date, int amountToSubtract) {
        return minus(date, DateAccuracy.DAY, 7 * amountToSubtract);
    }

    private static int selectField(DateAccuracy dateAccuracy) {
        switch (dateAccuracy) {
            case YEAR: {
                return Calendar.YEAR;
            }
            case MONTH: {
                return Calendar.MONTH;
            }
            case DAY: {
                return Calendar.DAY_OF_MONTH;
            }
            case HOUR: {
                return Calendar.HOUR_OF_DAY;
            }
            case MINUTE: {
                return Calendar.MINUTE;
            }
            case SECOND: {
                return Calendar.SECOND;
            }
            case MILLISECOND: {
                return Calendar.MILLISECOND;
            }
            default: {
                throw new IllegalArgumentException("未知时间精度");
            }
        }
    }

    /**
     * 给定日期上一周指定星期X的日期
     *
     * @param localDateTime 给定时间
     * @param dayOfWeek     星期几
     * @return 给定日期上个星期指定星期X日期
     */
    public static LocalDateTime dayInLastWeek(LocalDateTime localDateTime, DayOfWeek dayOfWeek) {
        return localDateTime.with(TemporalAdjusters.previous(DayOfWeek.SUNDAY)).with(TemporalAdjusters.previousOrSame(dayOfWeek));
    }

    /**
     * 给定日期当前周指定星期X的日期
     *
     * @param localDateTime 给定时间
     * @param dayOfWeek     星期几
     * @return 给定日期当前星期指定星期X日期
     */
    public static LocalDateTime dayInThisWeek(LocalDateTime localDateTime, DayOfWeek dayOfWeek) {
        return localDateTime.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).with(TemporalAdjusters.nextOrSame(dayOfWeek));
    }

    /**
     * 给定日期下一周指定星期X的日期
     *
     * @param localDateTime 给定时间
     * @param dayOfWeek     星期几
     * @return 给定日期下个星期指定星期X日期
     */
    public static LocalDateTime dayInNextWeek(LocalDateTime localDateTime, DayOfWeek dayOfWeek) {
        return localDateTime.with(TemporalAdjusters.next(DayOfWeek.MONDAY)).with(TemporalAdjusters.nextOrSame(dayOfWeek));
    }

    private static SimpleDateFormat getSimpleDateFormat(String formatStr) {
        return getSimpleDateFormat(formatStr, TimeZone.getDefault());
    }

    private static SimpleDateFormat getSimpleDateFormat(String formatStr, TimeZone timeZone) {
        Map<String, SimpleDateFormat> formatMap = FORMAT_THREAD_LOCAL.get();
        String key = formatStr + "%" + timeZone.getID();
        return formatMap.computeIfAbsent(key, k -> {
            SimpleDateFormat format = new SimpleDateFormat(formatStr);
            format.setTimeZone(timeZone);
            return format;
        });
    }

    private DatetimeUtils() {
    }

}
