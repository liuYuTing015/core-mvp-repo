package com.core.app.data;

import com.core.app.ui.model.User;

public interface UserData {
    void getDataSuccess(User response);

    void getDataFail(String msg);
}
