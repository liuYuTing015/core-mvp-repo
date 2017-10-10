package com.core.app.presenter;

import com.core.app.base.BasePresenter;
import com.core.app.data.UserData;
import com.core.app.service.ApiCallback;
import com.core.app.model.User;

public class UserPresenter extends BasePresenter<UserData> {

    public UserPresenter(UserData view) {
        attachView(view);
    }

    public void restAttachView(UserData view) {
        attachView(view);
    }

    public void loadMainBanner() {
        addSubscription(mApiService.loadUser(), new ApiCallback<User>() {
            @Override
            public void onSuccess(User response) {

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
