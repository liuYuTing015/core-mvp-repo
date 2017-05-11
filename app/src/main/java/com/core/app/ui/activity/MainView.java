package com.core.app.ui.activity;

import com.core.app.service.MainModel;

public interface MainView {
    void getDataSuccess(MainModel response);

    void getDataFail(String msg);
}
