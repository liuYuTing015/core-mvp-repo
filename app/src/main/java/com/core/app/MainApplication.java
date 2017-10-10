package com.core.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.core.app.base.ModelApplication;
import com.core.app.message.Messenger;
import com.core.app.ui.component.DaggerMainAppComponent;
import com.core.app.ui.component.MainAppComponent;
import com.core.app.ui.module.HttpModule;
import com.core.app.ui.module.MainAppModule;

import java.util.HashSet;
import java.util.Set;

import io.realm.Realm;


/**
 * Created by Ting on 17/5/9.
 */

public class MainApplication extends Application {

    private static MainApplication sApplication;
    private static MainAppComponent sAppComponent;
    private static String sCacheDir;
    private Set<Activity> sAllActivities;

    public static int SCREEN_WIDTH = -1;
    public static int SCREEN_HEIGHT = -1;
    public static float DIMEN_RATE = -1.0F;
    public static int DIMEN_DPI = -1;
    private Messenger mMessenger = new Messenger();

    public static MainApplication getInstance() {
        return sApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;

        //初始化屏幕宽高
        getScreenSize();

        //初始化数据库
        Realm.init(getApplicationContext());

        /**
         * 如果存在SD卡则将缓存写入SD卡,否则写入手机内存
         */
        if (getApplicationContext().getExternalCacheDir() != null && ExistSDCard()) {
            sCacheDir = getApplicationContext().getExternalCacheDir().toString();
        } else {
            sCacheDir = getApplicationContext().getCacheDir().toString();
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public void addActivity(Activity act) {
        if (sAllActivities == null) {
            sAllActivities = new HashSet<>();
        }
        sAllActivities.add(act);
    }

    public void removeActivity(Activity act) {
        if (sAllActivities != null) {
            sAllActivities.remove(act);
        }
    }

    public void exitApp() {
        if (sAllActivities != null) {
            synchronized (sAllActivities) {
                for (Activity act : sAllActivities) {
                    act.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    public Messenger getMessenger() {
        return mMessenger;
    }

    public void getScreenSize() {
        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        Display display = windowManager.getDefaultDisplay();
        display.getMetrics(dm);
        DIMEN_RATE = dm.density / 1.0F;
        DIMEN_DPI = dm.densityDpi;
        SCREEN_WIDTH = dm.widthPixels;
        SCREEN_HEIGHT = dm.heightPixels;
        if (SCREEN_WIDTH > SCREEN_HEIGHT) {
            int t = SCREEN_HEIGHT;
            SCREEN_HEIGHT = SCREEN_WIDTH;
            SCREEN_WIDTH = t;
        }
    }

    public static String getAppCacheDir() {
        return sCacheDir;
    }

    private boolean ExistSDCard() {
        return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
    }

    public static MainAppComponent getAppComponent() {
        if (sAppComponent == null) {
            sAppComponent = DaggerMainAppComponent.builder()
                    .mainAppModule(new MainAppModule(sApplication))
                    .httpModule(new HttpModule())
                    .build();
        }
        return sAppComponent;
    }
}
