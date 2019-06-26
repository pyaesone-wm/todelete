package com.ps.todelete.data.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.ps.todelete.data.vos.NewsVO;
import com.ps.todelete.events.RestApiEvents;
import com.ps.todelete.network.ToDeleteDataAgent;
import com.ps.todelete.network.ToDeleteDataAgentImpl;
import com.ps.todelete.network.responses.GetNewsResponse;
import com.ps.todelete.utils.RestapiConstants;;import org.greenrobot.eventbus.EventBus;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by pyaesone on 2019-06-23
 */
public class ToDeleteModel {

    private static ToDeleteModel objInstance;

    private ToDeleteDataAgent toDeleteDataAgent;

    private MutableLiveData<List<NewsVO>> newsListObserable;


    public static ToDeleteModel getInstance() {
        if (objInstance == null) {
            objInstance = new ToDeleteModel();
        }

        return objInstance;
    }

    private ToDeleteModel() {
        toDeleteDataAgent = ToDeleteDataAgentImpl.getInstance();

        newsListObserable = new MutableLiveData<>();

        EventBus eventBus = EventBus.getDefault();
        if (!eventBus.isRegistered(this)) {
//            EventBus.getDefault().register(this);
        }
    }

    public void startLoadingNews(Context context) {
        toDeleteDataAgent.loadAllNewsList(RestapiConstants.API_KEY, "10", "1", "US", context);
    }

    public LiveData<List<NewsVO>> startLoadingNewsWithRx(final Context context) {
        Observable<GetNewsResponse> getNewsResponseObservable = ToDeleteDataAgentImpl.getInstance()
                .getToDeleteApi().loadAllNewsListRx(RestapiConstants.API_KEY, "10", "1", "US");
        getNewsResponseObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetNewsResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GetNewsResponse getNewsResponse) {
                        if (getNewsResponse != null && !getNewsResponse.getNewsList().isEmpty()) {
                            RestApiEvents.NewsListDataLoadedEvent newsListDataLoadedEvent = new RestApiEvents.NewsListDataLoadedEvent(
                                    getNewsResponse.getNewsList(), context);
                            newsListObserable.setValue(getNewsResponse.getNewsList());
//                            EventBus.getDefault().post(newsListDataLoadedEvent);
                        } else {
                            RestApiEvents.ErrorInvokingAPIEvent errorInvokingAPIEvent = new RestApiEvents.ErrorInvokingAPIEvent("No Data to load.");
                            EventBus.getDefault().post(errorInvokingAPIEvent);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return newsListObserable;
    }
}
