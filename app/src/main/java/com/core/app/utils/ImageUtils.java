package com.core.app.utils;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by Ting on 17/5/10.
 */

public class ImageUtils {

    public static void load(Context context, @DrawableRes int imageRes, ImageView view) {
        Glide.with(context).load(imageRes).crossFade().into(view);
    }

    public static void loadUrl(Context context, String url, ImageView view) {
        Glide.with(context).load(url).crossFade().into(view);
    }

    public static void clear(Context context) {
        Glide.get(context).clearMemory();
    }
}
