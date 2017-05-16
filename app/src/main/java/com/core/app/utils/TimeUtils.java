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
