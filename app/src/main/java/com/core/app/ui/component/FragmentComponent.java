package com.core.app.ui.component;

import android.app.Activity;

import com.core.app.ui.module.FragmentModule;
import com.core.app.ui.module.MainAppModule;
import com.core.app.ui.qualifier.FragmentScope;

import dagger.Component;

/**
 * Created by codeest on 16/8/7.
 */

@FragmentScope
@Component(dependencies = MainAppModule.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();

   // void inject(DailyFragment dailyFragment);

}
