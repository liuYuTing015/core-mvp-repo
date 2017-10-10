package com.core.app.ui.component;

import android.app.Activity;

import com.core.app.ui.activity.MainActivity;
import com.core.app.ui.module.ActivityModule;
import com.core.app.ui.module.MainAppModule;
import com.core.app.ui.qualifier.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(dependencies = MainAppModule.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

    void inject(MainActivity mainActivity);
}
