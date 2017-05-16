package com.core.app.base;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;

import com.core.app.BuildConfig;
import com.core.app.ui.model.AccessToken;
import com.core.app.ui.model.User;
import com.core.app.utils.PreferencesUtils;
import com.google.gson.Gson;

/**
 * Models App used.
 */

public class ModelApplication extends android.support.multidex.MultiDexApplication {
    boolean mSplashed;
    AccessToken mAccessToken;
    String mAuthToken;
    User mUser;

    @Override
    public void onCreate() {
        super.onCreate();
        initSplashed();
        initUser();
        initAccessToken();
        initAuthToken();
    }

    public void logout() {
        putAccessToken(null);
        putUser(null);
    }

    private void initUser() {
        final String user_text = PreferencesUtils.getString(this, "user_text");
        if (!TextUtils.isEmpty(user_text)) {
            mUser = new Gson().fromJson(user_text, User.class);
        } else {
            mUser = null;
        }
    }

    public void putUser(User user) {
        String user_text = "";
        if (user != null) {
            user_text = new Gson().toJson(user);
        }
        PreferencesUtils.putString(this, "user_text", user_text);
        mUser = user;
    }

    public User getUser() {
        return mUser;
    }

    private void initAccessToken() {
        final String access_token = PreferencesUtils.getString(this, "access_token");
        if (!TextUtils.isEmpty(access_token)) {
            mAccessToken = new Gson().fromJson(access_token, AccessToken.class);
        } else {
            mAccessToken = null;
        }
    }

    private void initAuthToken() {
        mAuthToken = PreferencesUtils.getString(this, "auth_token");
    }

    public void putAuthToken(String authToken) {
        PreferencesUtils.putString(this, "auth_token", authToken);
        mAuthToken = authToken;
    }

    public void putAccessToken(AccessToken accessToken) {
        String access_token = "";
        if (accessToken != null) {
            access_token = new Gson().toJson(accessToken);
        }
        PreferencesUtils.putString(this, "access_token", access_token);
        mAccessToken = accessToken;
    }

    public String getAuthToken() {
        return TextUtils.isEmpty(mAuthToken) ? "unsign" : mAuthToken;
    }

    public AccessToken getAccessToken() {
        return mAccessToken;
    }

    public String getAuthorization() {
        return mAccessToken == null ? "Bearer unsign" : mAccessToken.getAuthorization();
    }

    private void initSplashed() {
        mSplashed = PreferencesUtils.getBoolean(this, "splashed" + BuildConfig.VERSION_NAME);
    }

    public boolean getSplashed() {
        return mSplashed;
    }

    public void putSplashed(boolean splashed) {
        PreferencesUtils.putBoolean(this, "splashed" + BuildConfig.VERSION_NAME, splashed);
        mSplashed = splashed;
    }
}