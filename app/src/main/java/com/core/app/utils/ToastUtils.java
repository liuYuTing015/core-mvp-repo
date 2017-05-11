package com.core.app.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * @author wanggeng@nq.com
 */
public class ToastUtils {
    private static final String TAG = ToastUtils.class.getSimpleName();

    private ToastUtils() {
        throw new AssertionError();
    }

    public static void show(Context context, int resId) {
        show(context, context.getResources().getText(resId), Toast.LENGTH_SHORT);
    }

    public static void show(Context context, int resId, int duration) {
        show(context, context.getResources().getText(resId), duration);
    }

    public static void show(Context context, CharSequence text) {
        show(context, text, Toast.LENGTH_SHORT);
    }

    public static void show(Context context, CharSequence text, int duration) {
        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

    public static void show(Context context, int resId, Object... args) {
        show(context, String.format(context.getResources().getString(resId), args), Toast.LENGTH_SHORT);
    }

    public static void show(Context context, String format, Object... args) {
        show(context, String.format(format, args), Toast.LENGTH_SHORT);
    }

    public static void show(Context context, int resId, int duration, Object... args) {
        show(context, String.format(context.getResources().getString(resId), args), duration);
    }

    public static void show(Context context, String format, int duration, Object... args) {
        show(context, String.format(format, args), duration);
    }

    public static void showImage(Context context, CharSequence text,int resource){
        showImage(context,text,Toast.LENGTH_SHORT,resource);
    }

    public static void showImage(Context context, CharSequence text, int duration,int resource){
        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.CENTER,0,0);
        LinearLayout toastView = (LinearLayout)toast.getView();
        ImageView image = new ImageView(context);
        image.setImageResource(resource);
        toastView.addView(image,0);
        toast.show();
    }
}