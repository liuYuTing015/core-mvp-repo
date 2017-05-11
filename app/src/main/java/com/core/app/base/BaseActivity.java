package com.core.app.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.core.app.utils.ImageUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class BaseActivity extends AppCompatActivity {

    private static final String TAG = BaseActivity.class.getSimpleName();

    //该方法需要添加
    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        ImageUtils.clear(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "=========== onDestroy ===========>");
        ImageUtils.clear(this);
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEvent(Object event) {

    }

    public void replace(int id, Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(id, fragment)
                .commitAllowingStateLoss();
    }

    public Fragment getFragmentById(int id) {
        return getSupportFragmentManager().findFragmentById(id);
    }

    public TextView getTitleText() {
        return null;
    }

}
