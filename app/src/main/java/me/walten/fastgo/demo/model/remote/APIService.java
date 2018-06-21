package me.walten.fastgo.demo.model.remote;

import java.util.Map;

import io.reactivex.Flowable;
import me.walten.fastgo.demo.model.entity.Result;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/*
 * -----------------------------------------------------------------
 * Copyright by 2018 Walten, All rights reserved.
 * -----------------------------------------------------------------
 * desc:
 * -----------------------------------------------------------------
 * 2018/6/15 : Create APIService.java (Walten);
 * -----------------------------------------------------------------
 */public interface APIService {

     @GET("weatherApi")
     Flowable<Result<Object>> weatherApi(@QueryMap(encoded = true)Map<String,Object> params);

}
