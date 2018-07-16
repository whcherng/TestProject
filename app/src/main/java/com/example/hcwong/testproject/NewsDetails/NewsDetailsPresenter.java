package com.example.hcwong.testproject.NewsDetails;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class NewsDetailsPresenter implements NewsDetailsContract.Presenter {

    private  Context mContext;
    private NewsDetailsContract.View mView;



    public NewsDetailsPresenter(Context mContext, NewsDetailsContract.View mView) {
        this.mContext = mContext;
        this.mView = mView;
    }

    @Override
    public void getNews() {

    }

    @Override
    public void shareNews(String news) {
        Log.d("sha",news);
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Share News");
        sharingIntent.putExtra(Intent.EXTRA_TEXT,news );
        mContext.startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    @Override
    public void onViewActive(NewsDetailsContract.View view) {
        this.mView=mView;

    }

    @Override
    public void onViewInactive() {
        this.mView=null;

    }

    @Override
    public void start() {
        if(mView==null){
            return ;
        }
        mView.initView();
        mView.initListener();
        mView.renderView();
        getNews();

    }
}
