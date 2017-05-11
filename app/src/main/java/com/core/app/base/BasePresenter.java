package com.core.app.base;

import com.core.app.service.ApiService;
import com.core.app.service.RetrofitSingleton;

import rx.*;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Ting on 17/3/4.
 */

public class BasePresenter<V> {
    public V mMvpView;

    protected ApiService mApiService;
    protected CompositeSubscription mCompositeSubscription;

    public void attachView(V mvpView) {
        this.mMvpView = mvpView;
        mApiService = RetrofitSingleton.getInstance().getApiService();
    }

    public String getRange(int page){
        return "page:"+page+",max:10";
    }

    public void detachView() {
        this.mMvpView = null;
        onUnsubscribe();
    }

    //RXjava取消注册，以避免内存泄露
    public void onUnsubscribe() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }

    //RXjava注册
    public void addSubscription(Observable observable, Subscriber subscriber) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }
}
