package com.core.app.base;

import android.os.Bundle;

/**
 * MVP ----fragment
 */
public abstract class MvpFragment<P extends BasePresenter> extends BaseFragment {
    private static final String TAG = MvpFragment.class.getSimpleName();
    protected P mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mPresenter = createPresenter();
        super.onCreate(savedInstanceState);
    }

    protected abstract P createPresenter();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
