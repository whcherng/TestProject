package com.example.hcwong.testproject.Main.ListNews;

import android.content.Context;

import com.example.hcwong.testproject.Model.Article;

import java.util.List;

public class ListPresenter implements ListContract.Presenter, ListInteractor.ListInteractorCallBack {

    private final Context mContext;
    private ListContract.View mView;
    private ListInteractor mInteractor;

    public ListPresenter(Context mContext, ListContract.View mView) {
        this.mContext = mContext;
        this.mView = mView;
        this.mInteractor=new ListInteractor(mContext,this);
    }

    @Override
    public void onViewActive(ListContract.View view) {
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

    @Override
    public void getNews() {
        if(mView==null)
            return;
        mInteractor.getNews();
        mView.showLoading();
    }

    @Override
    public void onReturnNews(List<Article> articleList) {
        if(mView==null)
            return;
        mView.hideLoading();
        mView.setNewsList(articleList);
        if(articleList.size()==0)
            mView.showEmptyState();
        else{
            mView.hideEmptyState();
            mView.updateView();
        }
    }
}
