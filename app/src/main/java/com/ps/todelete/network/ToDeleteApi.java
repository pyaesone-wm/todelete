package com.ps.todelete.network;

import com.ps.todelete.network.responses.GetNewsResponse;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by pyaesone on 2019-06-23
 */
public interface ToDeleteApi {

    @GET("top-headlines?")
    Call<GetNewsResponse> loadAllNewsList(@Query("apikey")String apiKey,@Query("pagesize")String pageSize,@Query("page")String page,@Query("country")String country);

    @GET("top-headlines?")
    Observable<GetNewsResponse> loadAllNewsListRx(@Query("apikey")String apiKey, @Query("pagesize")String pageSize, @Query("page")String page, @Query("country")String country);
}
