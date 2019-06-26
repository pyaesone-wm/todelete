package com.ps.todelete.network.responses;

import com.google.gson.annotations.SerializedName;
import com.ps.todelete.data.vos.NewsVO;
import com.ps.todelete.network.NewsResponse;

import java.util.List;

/**
 * Created by pyaesone on 2019-06-23
 */
public class GetNewsResponse extends NewsResponse {

    @SerializedName("status")
    private String status;

    @SerializedName("totalResults")
    private int totalResults;

    @SerializedName("articles")
    private List<NewsVO> newsList;

    public String getStatus() {
        return status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public List<NewsVO> getNewsList() {
        return newsList;
    }
}
