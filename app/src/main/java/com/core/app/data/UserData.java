package com.core.app.data;

import com.core.app.model.User;

public interface UserData {
    void getDataSuccess(User response);

    void getDataFail(String msg);
}
