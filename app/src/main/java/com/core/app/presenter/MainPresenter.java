package com.core.app.presenter;

import com.core.app.base.BasePresenter;
import com.core.app.service.ApiCallback;
import com.core.app.ui.model.MainModel;
import com.core.app.data.MainData;

public class MainPresenter extends BasePresenter<MainData> {

    public MainPresenter(MainData view) {
        attachView(view);
    }

    public void restAttachView(MainData view) {
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
