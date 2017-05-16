package com.core.app;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.core.app.base.ModelApplication;
import com.core.app.message.Messenger;

/**
 * Created by Ting on 17/5/9.
 */

public class MainApplication extends ModelApplication {

    private static MainApplication sApplication;
    private static String sCacheDir;
    private Messenger mMessenger = new Messenger();

    public static MainApplication getInstance() {
        return sApplication;
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
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

    public Messenger getMessenger() {
        return mMessenger;
    }

    public static String getAppCacheDir() {
        return sCacheDir;
    }

    private boolean ExistSDCard() {
        return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
    }
}
