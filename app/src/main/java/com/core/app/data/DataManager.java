package com.core.app.data;

import com.core.app.model.DailyBeforeListBean;
import com.core.app.model.DailyListBean;
import com.core.app.model.DetailExtraBean;
import com.core.app.model.GoldManagerBean;
import com.core.app.model.NodeListBean;
import com.core.app.model.RealmLikeBean;
import com.core.app.model.RepliesListBean;
import com.core.app.model.SectionChildListBean;
import com.core.app.model.SectionListBean;
import com.core.app.model.ThemeChildListBean;
import com.core.app.model.ThemeListBean;
import com.core.app.model.WelcomeBean;
import com.core.app.model.ZhihuDetailBean;
import com.core.app.service.HttpHelper;
import com.core.app.utils.PreferencesHelper;

import java.util.List;

import io.reactivex.Flowable;

public class DataManager implements HttpHelper, DBHelper, PreferencesHelper {

    HttpHelper mHttpHelper;
    DBHelper mDbHelper;
    PreferencesHelper mPreferencesHelper;

    public DataManager(HttpHelper httpHelper, DBHelper dbHelper, PreferencesHelper preferencesHelper) {
        mHttpHelper = httpHelper;
        mDbHelper = dbHelper;
        mPreferencesHelper = preferencesHelper;
    }

    @Override
    public boolean getNightModeState() {
        return mPreferencesHelper.getNightModeState();
    }

    @Override
    public void setNightModeState(boolean state) {
        mPreferencesHelper.setNightModeState(state);
    }

    @Override
    public boolean getNoImageState() {
        return mPreferencesHelper.getNoImageState();
    }

    @Override
    public void setNoImageState(boolean state) {
        mPreferencesHelper.setNoImageState(state);
    }

    @Override
    public boolean getAutoCacheState() {
        return mPreferencesHelper.getAutoCacheState();
    }

    @Override
    public void setAutoCacheState(boolean state) {
        mPreferencesHelper.setAutoCacheState(state);
    }

    @Override
    public int getCurrentItem() {
        return mPreferencesHelper.getCurrentItem();
    }

    @Override
    public void setCurrentItem(int item) {
        mPreferencesHelper.setCurrentItem(item);
    }

    @Override
    public boolean getLikePoint() {
        return mPreferencesHelper.getLikePoint();
    }

    @Override
    public void setLikePoint(boolean isFirst) {
        mPreferencesHelper.setLikePoint(isFirst);
    }

    @Override
    public boolean getVersionPoint() {
        return mPreferencesHelper.getVersionPoint();
    }

    @Override
    public void setVersionPoint(boolean isFirst) {
        mPreferencesHelper.setVersionPoint(isFirst);
    }

    @Override
    public boolean getManagerPoint() {
        return mPreferencesHelper.getManagerPoint();
    }

    @Override
    public void setManagerPoint(boolean isFirst) {
        mPreferencesHelper.setManagerPoint(isFirst);
    }

    @Override
    public void insertNewsId(int id) {
        mDbHelper.insertNewsId(id);
    }

    @Override
    public boolean queryNewsId(int id) {
        return mDbHelper.queryNewsId(id);
    }

    @Override
    public void insertLikeBean(RealmLikeBean bean) {
        mDbHelper.insertLikeBean(bean);
    }

    @Override
    public void deleteLikeBean(String id) {
        mDbHelper.deleteLikeBean(id);
    }

    @Override
    public boolean queryLikeId(String id) {
        return mDbHelper.queryLikeId(id);
    }

    @Override
    public List<RealmLikeBean> getLikeList() {
        return mDbHelper.getLikeList();
    }

    @Override
    public void changeLikeTime(String id, long time, boolean isPlus) {
        mDbHelper.changeLikeTime(id, time, isPlus);
    }

    @Override
    public void updateGoldManagerList(GoldManagerBean bean) {
        mDbHelper.updateGoldManagerList(bean);
    }

    @Override
    public GoldManagerBean getGoldManagerList() {
        return mDbHelper.getGoldManagerList();
    }

    @Override
    public Flowable<DailyListBean> fetchDailyListInfo() {
        return mHttpHelper.fetchDailyListInfo();
    }

    @Override
    public Flowable<ZhihuDetailBean> fetchDetailInfo(int id) {
        return mHttpHelper.fetchDetailInfo(id);
    }

    @Override
    public Flowable<DetailExtraBean> fetchDetailExtraInfo(int id) {
        return mHttpHelper.fetchDetailExtraInfo(id);
    }

    @Override
    public Flowable<WelcomeBean> fetchWelcomeInfo(String res) {
        return mHttpHelper.fetchWelcomeInfo(res);
    }

}
