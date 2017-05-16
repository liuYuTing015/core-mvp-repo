package com.core.app.data;

import com.core.app.ui.model.MainModel;

public interface MainData {
    void getDataSuccess(MainModel response);

    void getDataFail(String msg);
}
