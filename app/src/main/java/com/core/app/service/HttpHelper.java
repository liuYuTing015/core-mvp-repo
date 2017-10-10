package com.core.app.service;

import com.core.app.model.DailyListBean;
import com.core.app.model.DetailExtraBean;
import com.core.app.model.WelcomeBean;
import com.core.app.model.ZhihuDetailBean;

import io.reactivex.Flowable;

/**
 * Created by Ting on 17/6/7.
 */

public interface HttpHelper {

    Flowable<WelcomeBean> fetchWelcomeInfo(String res);

    Flowable<DailyListBean> fetchDailyListInfo();

    Flowable<ZhihuDetailBean> fetchDetailInfo(int id);

    Flowable<DetailExtraBean> fetchDetailExtraInfo(int id);
}
