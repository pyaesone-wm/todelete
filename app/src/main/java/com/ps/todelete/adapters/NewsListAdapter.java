package com.ps.todelete.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ps.todelete.R;
import com.ps.todelete.data.vos.NewsVO;
import com.ps.todelete.viewholders.NewsViewHolder;

/**
 * Created by pyaesone on 2019-06-23
 */
public class NewsListAdapter extends BaseRecyclerAdapter<NewsViewHolder, NewsVO> {

    public NewsListAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_news, parent, false);

        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 7;
    }
}
