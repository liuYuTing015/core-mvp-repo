package com.core.app.data.api;


import com.core.app.data.response.WXHttpResponse;
import com.core.app.model.WXItemBean;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by codeest on 16/8/28.
 */

public interface WeChatApis {

//    String HOST = "http://apis.baidu.com/txapi/weixin/";
    String HOST = "http://api.tianapi.com/";

    /**
     * 微信精选列表
     */
    @GET("wxnew")
    Flowable<WXHttpResponse<List<WXItemBean>>> getWXHot(@Query("key") String key, @Query("num") int num, @Query("page") int page);

    /**
     * 微信精选列表
     */
    @GET("wxnew")
    Flowable<WXHttpResponse<List<WXItemBean>>> getWXHotSearch(@Query("key") String key, @Query("num") int num, @Query("page") int page, @Query("word") String word);
}
