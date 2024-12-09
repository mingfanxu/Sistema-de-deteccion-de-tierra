package com.wing.yjyw.utils;



import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 日期处理
 *
 * @author mingfanxu@gmail.com
 */
public class DateUtils {
    /** 时间格式(yyyy-MM-dd) */
    public final static String DATE_PATTERN = "yyyy-MM-dd";

    /** 时间格式(HH:mm:ss) */
    public final static String TIME_PATTERN = "HH:mm:ss";

    /** 时间格式(yyyy-MM-dd HH:mm:ss) */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String dateToString(LocalDateTime dateTime, String format){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return dateTime.format(formatter);
    }

    public static String getNow() {
        // 获取当前日期时间
        LocalDateTime currentDateTime = LocalDateTime.now();
        // 定义日期时间格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
        // 格式化日期时间
        return currentDateTime.format(formatter);
    }

}
