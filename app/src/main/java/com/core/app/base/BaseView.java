package com.core.app.base;

/**
 * Created by Ting on 17/6/8.
 */

public interface BaseView {
    void showErrorMsg(String msg);

    void useNightMode(boolean isNight);

    //=======  State  =======
    void stateError();

    void stateEmpty();

    void stateLoading();

    void stateMain();
}
