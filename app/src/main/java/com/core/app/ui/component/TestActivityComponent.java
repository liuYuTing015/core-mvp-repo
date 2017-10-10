package com.core.app.ui.component;

import com.core.app.ui.activity.DaggerActivity;

import dagger.Component;

/**
 * Created by Ting on 17/5/31.
 */
@Component
public interface TestActivityComponent {
    void inject(DaggerActivity daggerActivity);
}
