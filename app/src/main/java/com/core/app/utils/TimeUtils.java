package com.core.app.utils;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.util.Log;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by dandanba on 12/7/15.
 */
public class TimeUtils {
    private static final String TAG = TimeUtils.class.getSimpleName();

    public final static SimpleDateFormat SIMPLE_DATE_MONTH = new SimpleDateFormat("MM/dd");
    public final static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    public final static SimpleDateFormat SIMPLE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public final static SimpleDateFormat SIMPLE_SECOND_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public final static SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    public final static SimpleDateFormat SIMPLE_DATE_TAME_FORMAT = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");

    static {
        TIME_FORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    public static Date parseDate(String time) {
        Date date = new Date();
        if (!TextUtils.isEmpty(time)) {
            try {
                date = TIME_FORMAT.parse(time);
            } catch (ParseException e) {
                Log.e(TAG, "parseDate", e);
            }
        }
        return date;
    }

    public static String convertDate(String time) {
        Date date = new Date();
        try {
            date = TimeUtils.SIMPLE_DATE_FORMAT.parse(time);
        } catch (ParseException e) {
            Log.e(TAG, "convertDate", e);
        }
        return TimeUtils.TIME_FORMAT.format(date);
    }

    public static String converDateTime(String time){
        Date date = new Date();
        try {
            date = TimeUtils.SIMPLE_DATE_TAME_FORMAT.parse(time);
        } catch (ParseException e) {
            Log.e(TAG, "convertDate", e);
        }
        return TimeUtils.TIME_FORMAT.format(date);
    }

    public static String stampToDate(String stamp){
        long lt = Long.parseLong(stamp);
        Date date = new Date(lt);
        String res = SIMPLE_DATE_FORMAT.format(date);
        return res;
    }

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    @SuppressLint("SimpleDateFormat")
    public static String getNowYMDHMSTime() {
        SimpleDateFormat mDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        return mDateFormat.format(new Date());
    }

    /**
     * MM-dd HH:mm:ss
     */
    @SuppressLint("SimpleDateFormat")
    public static String getNowMDHMSTime() {
        SimpleDateFormat mDateFormat = new SimpleDateFormat(
                "MM-dd HH:mm:ss");
        return mDateFormat.format(new Date());
    }

    /**
     * MM-dd
     */
    @SuppressLint("SimpleDateFormat")
    public static String getNowYMD() {

        SimpleDateFormat mDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd");
        return mDateFormat.format(new Date());
    }

    /**
     * yyyy-MM-dd
     */
    @SuppressLint("SimpleDateFormat")
    public static String getYMD(Date date) {

        SimpleDateFormat mDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd");
        return mDateFormat.format(date);
    }

    @SuppressLint("SimpleDateFormat")
    public static String getMD(Date date) {

        SimpleDateFormat mDateFormat = new SimpleDateFormat(
                "MM-dd");
        return mDateFormat.format(date);
    }

}
