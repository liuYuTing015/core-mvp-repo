package com.core.app.ui.module;

import android.util.Log;

import com.core.app.BuildConfig;
import com.core.app.MainApplication;
import com.core.app.data.api.GankApis;
import com.core.app.data.api.GoldApis;
import com.core.app.data.api.WeChatApis;
import com.core.app.data.api.ZhihuApis;
import com.core.app.service.Header;
import com.core.app.ui.qualifier.GankUrl;
import com.core.app.ui.qualifier.GoldUrl;
import com.core.app.ui.qualifier.TestUrl;
import com.core.app.ui.qualifier.WechatUrl;
import com.core.app.ui.qualifier.ZhihuUrl;
import com.core.app.utils.CommonUtil;
import com.core.app.utils.HttpLogger;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ting on 17/6/7.
 */
@Module
public class HttpModule {

    @Singleton
    @Provides
    Retrofit.Builder provideRetrofitBuild() {
        return new Retrofit.Builder();
    }

    @Singleton
    @Provides
    OkHttpClient.Builder provideOkHttpBuilder() {
        return new OkHttpClient.Builder();
    }

    @Singleton
    @Provides
    @ZhihuUrl
    Retrofit provideZhihuRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, ZhihuApis.HOST);
    }

    @Singleton
    @Provides
    @WechatUrl
    Retrofit provideWechatRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, WeChatApis.HOST);
    }

    @Singleton
    @Provides
    @GankUrl
    Retrofit provideGankRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, GankApis.HOST);
    }

    @Singleton
    @Provides
    @GoldUrl
    Retrofit provideGoldRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, GoldApis.HOST);
    }

    @Singleton
    @Provides
    OkHttpClient provideClient(OkHttpClient.Builder builder) {
//        if (BuildConfig.DEBUG) {
//            HttpLogger loggingInterceptor = new HttpLogger(headers);
////            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
//            builder.addInterceptor(loggingInterceptor);
//        }

        File cacheFile = new File(MainApplication.getAppCacheDir(), "/NetCache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        Interceptor cacheInterceptor = chain -> {
            Request request = chain.request();
            if (!CommonUtil.isNetworkConnected(MainApplication.getInstance())) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            Response response = chain.proceed(request);
            Response.Builder newBuilder = response.newBuilder();
            if (CommonUtil.isNetworkConnected(MainApplication.getInstance())) {
                int maxAge = 0;
                // 有网络时 设置缓存超时时间0个小时
                newBuilder.header("Cache-Control", "public, max-age=" + maxAge).removeHeader("Pragma");
            } else {
                // 无网络时，设置超时为4周
                int maxStale = 60 * 60 * 24 * 28;
                newBuilder.header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale).removeHeader("Pragma");
            }
            return newBuilder.build();
        };
        Interceptor requestInterceptor = chain -> {
            Request request = chain.request()
                    .newBuilder()
                    //.addHeader("Accept", "application/vnd.yuanzi.v4+json")
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Range", "page:1,max:10")
                    .addHeader("Authorization", "")
                    .build();
            Log.v("zcb", "request:" + request.toString());
            Log.v("zcb", "request headers:" + request.headers().toString());
            return chain.proceed(request);
        };
        builder.cache(cache).addInterceptor(cacheInterceptor);
        builder.addNetworkInterceptor(requestInterceptor);

        //设置超时
        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);
        return builder.build();
    }

    @Singleton
    @Provides
    ZhihuApis provideZhihuService(@ZhihuUrl Retrofit retrofit) {
        return retrofit.create(ZhihuApis.class);
    }

    @Singleton
    @Provides
    GankApis provideGankService(@GankUrl Retrofit retrofit) {
        return retrofit.create(GankApis.class);
    }

    @Singleton
    @Provides
    WeChatApis provideWechatService(@WechatUrl Retrofit retrofit) {
        return retrofit.create(WeChatApis.class);
    }

    @Singleton
    @Provides
    GoldApis provideGoldService(@GoldUrl Retrofit retrofit) {
        return retrofit.create(GoldApis.class);
    }

    private Retrofit createRetrofit(Retrofit.Builder builder, OkHttpClient client, String url) {
        return builder
                .baseUrl(url)
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
