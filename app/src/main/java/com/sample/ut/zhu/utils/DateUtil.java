package com.sample.ut.zhu.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * date util
 *
 * @author Nafeng zhu
 * on 2017/1/21 13:14
 */
public class DateUtil {

    /**
     * such as ：2017-11-01 22:11:00
     */
    public static String FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss";

    /**
     * such as （"2017-11-01 22:11:00"）
     *
     * @param time
     * @return timestamp
     */
    public static long dateToStamp(String time) throws ParseException{
        SimpleDateFormat sdr = new SimpleDateFormat(FORMAT_YMDHMS, Locale.CHINA);
        Date date = sdr.parse(time);
        return date.getTime();
    }

    /**
     * exchange long type to data type
     */
    public static String stampToDate(long lt){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_YMDHMS, Locale.CHINA);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

}