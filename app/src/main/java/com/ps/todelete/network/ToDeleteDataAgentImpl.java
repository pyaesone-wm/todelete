package com.ps.todelete.network;

import android.content.Context;

import com.google.gson.Gson;
import com.ps.networkutils.NetworkUtilss;
import com.ps.todelete.events.RestApiEvents;
import com.ps.todelete.network.responses.GetNewsResponse;
import com.ps.todelete.utils.RestapiConstants;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by pyaesone on 2019-06-24
 */
public class ToDeleteDataAgentImpl implements ToDeleteDataAgent {

    private ToDeleteApi toDeleteApi;

    private static ToDeleteDataAgentImpl objInstance;

    private ToDeleteDataAgentImpl() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RestapiConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        toDeleteApi = retrofit.create(ToDeleteApi.class);
    }

    public static ToDeleteDataAgentImpl getInstance() {
        if (objInstance == null) {
            objInstance = new ToDeleteDataAgentImpl();
        }

        return objInstance;
    }


    @Override
    public void loadAllNewsList(String apiKey, String pageSize, String page, String country, final Context context) {
        if (NetworkUtilss.getInstance().checkConnection(context)) {
            Call<GetNewsResponse> loadAllNewsList = toDeleteApi.loadAllNewsList(apiKey, pageSize, page, country);
            loadAllNewsList.enqueue(new ToDeleteCallBack<GetNewsResponse>() {
                @Override
                public void onResponse(Call<GetNewsResponse> call, Response<GetNewsResponse> response) {
                    super.onResponse(call, response);
                    GetNewsResponse newsResponse = response.body();
                    if (newsResponse != null && !newsResponse.getNewsList().isEmpty()) {
                        RestApiEvents.NewsListDataLoadedEvent newsListDataLoadedEvent = new RestApiEvents.NewsListDataLoadedEvent(
                                newsResponse.getNewsList(), context);
                        EventBus.getDefault().post(newsListDataLoadedEvent);
                    } else {
                        RestApiEvents.ErrorInvokingAPIEvent errorInvokingAPIEvent = new RestApiEvents.ErrorInvokingAPIEvent("No Data to load.");
                        EventBus.getDefault().post(errorInvokingAPIEvent);
                    }
                }
            });
        } else {
            RestApiEvents.ErrorInvokingAPIEvent errorInvokingAPIEvent = new RestApiEvents.ErrorInvokingAPIEvent("No internet access.");
            EventBus.getDefault().post(errorInvokingAPIEvent);
        }
    }

    public ToDeleteApi getToDeleteApi() {
        return toDeleteApi;
    }
}
