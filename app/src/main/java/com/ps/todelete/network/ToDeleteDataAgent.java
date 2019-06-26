package com.ps.todelete.network;

import android.content.Context;

/**
 * Created by pyaesone on 2019-06-24
 */
public interface ToDeleteDataAgent {

    void loadAllNewsList(String apiKey, String pageSize, String page,String country, Context context);
}
