package com.core.app.service;

import com.core.app.data.api.GankApis;
import com.core.app.data.api.GoldApis;
import com.core.app.data.api.WeChatApis;
import com.core.app.data.api.ZhihuApis;
import com.core.app.model.DailyListBean;
import com.core.app.model.DetailExtraBean;
import com.core.app.model.WelcomeBean;
import com.core.app.model.ZhihuDetailBean;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * Created by Ting on 17/6/7.
 */

public class RetrofitHelper implements HttpHelper {

    private ZhihuApis mZhihuApiService;
    private GankApis mGankApiService;
    private WeChatApis mWechatApiService;
    private GoldApis mGoldApiService;

    @Inject
    public RetrofitHelper(ZhihuApis zhihuApiService, GankApis gankApiService, WeChatApis wechatApiService, GoldApis goldApiService) {
        this.mZhihuApiService = zhihuApiService;
        this.mGankApiService = gankApiService;
        this.mWechatApiService = wechatApiService;
        this.mGoldApiService = goldApiService;
    }


    @Override
    public Flowable<WelcomeBean> fetchWelcomeInfo(String res) {
        return mZhihuApiService.getWelcomeInfo(res);
    }

    @Override
    public Flowable<DailyListBean> fetchDailyListInfo() {
        return mZhihuApiService.getDailyList();
    }

    @Override
    public Flowable<ZhihuDetailBean> fetchDetailInfo(int id) {
        return mZhihuApiService.getDetailInfo(id);
    }

    @Override
    public Flowable<DetailExtraBean> fetchDetailExtraInfo(int id) {
        return mZhihuApiService.getDetailExtraInfo(id);
    }
}
