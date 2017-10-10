package com.core.app.ui.component;

import com.core.app.MainApplication;
import com.core.app.base.App;
import com.core.app.data.DataManager;
import com.core.app.data.RealmHelper;
import com.core.app.service.RetrofitHelper;
import com.core.app.ui.module.HttpModule;
import com.core.app.ui.module.MainAppModule;
import com.core.app.utils.ImplPreferencesHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Ting on 17/6/8.
 */
@Singleton
@Component(modules = {MainAppModule.class, HttpModule.class})
public interface MainAppComponent {
    MainApplication getContext();  // 提供App的Context

    DataManager getDataManager(); //数据中心

    RetrofitHelper retrofitHelper();  //提供http的帮助类

    RealmHelper realmHelper();    //提供数据库帮助类

    ImplPreferencesHelper preferencesHelper(); //提供sp帮助类


}
