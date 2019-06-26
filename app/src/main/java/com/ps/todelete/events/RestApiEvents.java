package com.ps.todelete.events;

import android.content.Context;

import com.ps.todelete.data.vos.NewsVO;

import java.util.List;

/**
 * Created by pyaesone on 2019-06-24
 */
public class RestApiEvents {

    public static class EmptyResponseEvent {

    }

    public static class ErrorInvokingAPIEvent {
        private String errorMsg;

        public ErrorInvokingAPIEvent(String errorMsg) {
            this.errorMsg = errorMsg;
        }

        public String getErrorMsg() {
            return errorMsg;
        }
    }

    public static class NewsListDataLoadedEvent {
        //        private int loadedPageIndex;
        private List<NewsVO> loadedNewsList;
        private Context context;

        public NewsListDataLoadedEvent(List<NewsVO> newsList, Context context) {
//            this.loadedPageIndex = loadedPageIndex;
            this.loadedNewsList = newsList;
            this.context = context;
        }

        public List<NewsVO> getLoadedNewsList() {
            return loadedNewsList;
        }

        public Context getContext() {
            return context;
        }
    }
}
