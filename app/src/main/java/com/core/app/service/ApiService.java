package com.core.app.service;

import retrofit2.http.*;
import rx.Observable;

/**
 * Created by Ting on 17/3/14.
 */
public interface ApiService {

    @GET("adat/sk/{cityId}.html")
    Observable<MainModel> loadDataByRetrofit(@Path("cityId") String cityId);

}
