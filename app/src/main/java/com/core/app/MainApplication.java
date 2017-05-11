package com.core.app;

import android.app.Application;

/**
 * Created by Ting on 17/5/9.
 */

public class MainApplication extends Application {

    private static MainApplication sApplication;
    private static String sCacheDir;

    public static MainApplication getInstance() {
        return sApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        sApplication = this;
        /**
         * 如果存在SD卡则将缓存写入SD卡,否则写入手机内存
         */
        if (getApplicationContext().getExternalCacheDir() != null && ExistSDCard()) {
            sCacheDir = getApplicationContext().getExternalCacheDir().toString();
        } else {
            sCacheDir = getApplicationContext().getCacheDir().toString();
        }
    }

    public static String getAppCacheDir() {
        return sCacheDir;
    }

    private boolean ExistSDCard() {
        return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
    }
}
