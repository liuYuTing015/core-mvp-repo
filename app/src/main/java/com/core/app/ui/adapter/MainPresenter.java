package com.core.app.ui.adapter;

import android.content.Context;

import com.core.app.base.BasePresenter;
import com.core.app.service.ApiCallback;
import com.core.app.service.MainModel;
import com.core.app.ui.activity.MainView;

public class MainPresenter extends BasePresenter<MainView> {

    public MainPresenter(MainView view) {
        attachView(view);
    }

    public void restAttachView(MainView view) {
        attachView(view);
    }

    public void loadMainBanner() {
        addSubscription(mApiService.loadDataByRetrofit("101220602"), new ApiCallback<MainModel>() {
            @Override
            public void onSuccess(MainModel response) {

                mMvpView.getDataSuccess(response);
            }

            @Override
            public void onFailure(String msg) {
                mMvpView.getDataFail(msg);

            }

            @Override
            public void onFinish() {

            }
        });
    }
}
