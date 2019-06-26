package com.ps.todelete.activities;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.ps.networkutils.NetworkUtilss;
import com.ps.todelete.R;
import com.ps.todelete.adapters.NewsListAdapter;
import com.ps.todelete.components.EmptyViewPod;
import com.ps.todelete.components.rvset.SmartRecyclerView;
import com.ps.todelete.data.model.ToDeleteModel;
import com.ps.todelete.data.vos.NewsVO;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv_news)
    SmartRecyclerView rvNews;

    @BindView(R.id.vp_news_list)
    EmptyViewPod vpNewsList;

    private NewsListAdapter newsListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this, this);


        //TODO to delete after test
        NetworkUtilss.getInstance().checkConnection(getBaseContext());

        vpNewsList.setEmptyData("No News to load");
        rvNews.setEmptyView(vpNewsList);
        rvNews.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false));
        newsListAdapter = new NewsListAdapter(getBaseContext());
        rvNews.setAdapter(newsListAdapter);

        ToDeleteModel.getInstance().startLoadingNewsWithRx(getBaseContext()).observe(this, new Observer<List<NewsVO>>() {
            @Override
            public void onChanged(@Nullable List<NewsVO> newsVOS) {
                Toast.makeText(getApplicationContext(), "News list size from network : " + newsVOS.size(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
//        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
//        EventBus.getDefault().unregister(this);
    }
}
