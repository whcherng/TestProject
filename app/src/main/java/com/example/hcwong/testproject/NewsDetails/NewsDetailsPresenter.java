package com.example.hcwong.testproject.NewsDetails;

import android.content.Context;

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
