package com.core.app.ui.module;

import com.core.app.MainApplication;
import com.core.app.data.DBHelper;
import com.core.app.data.DataManager;
import com.core.app.data.RealmHelper;
import com.core.app.service.HttpHelper;
import com.core.app.service.RetrofitHelper;
import com.core.app.utils.ImplPreferencesHelper;
import com.core.app.utils.PreferencesHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ting on 17/6/8.
 */
@Module
public class MainAppModule {
    private final MainApplication application;

    public MainAppModule(MainApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    MainApplication provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    HttpHelper provideHttpHelper(RetrofitHelper retrofitHelper) {
        return retrofitHelper;
    }

    @Provides
    @Singleton
    DBHelper provideDBHelper(RealmHelper realmHelper) {
        return realmHelper;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(ImplPreferencesHelper implPreferencesHelper) {
        return implPreferencesHelper;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(HttpHelper httpHelper, DBHelper DBHelper, PreferencesHelper preferencesHelper) {
        return new DataManager(httpHelper, DBHelper, preferencesHelper);
    }

}
