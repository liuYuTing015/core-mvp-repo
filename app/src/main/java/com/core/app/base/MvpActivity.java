package com.core.app.base;

import android.os.Bundle;

/**
 * MVP ----fragment
 */
public abstract class MvpActivity<P extends BasePresenter> extends BaseActivity {
    private static final String TAG = MvpActivity.class.getSimpleName();
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
