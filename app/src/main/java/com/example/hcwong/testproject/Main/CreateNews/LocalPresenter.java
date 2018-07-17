package com.example.hcwong.testproject.Main.CreateNews;

import android.content.Context;

import com.example.hcwong.testproject.LocalDb.DbHelper;
import com.example.hcwong.testproject.Model.Article;

import java.util.List;

import javax.inject.Inject;

public class LocalPresenter implements LocalContract.Presenter, LocalInteractor.NewsInteractorCallBack {

    private Context mContext;
    private LocalContract.View mView;
    private LocalInteractor mInteractor;

    @Inject
    public LocalPresenter(Context mContext, LocalContract.View mView) {
        this.mContext = mContext;
        this.mView = mView;
        this.mInteractor=new LocalInteractor(mContext,this);
    }

    @Override
    public void onViewActive(LocalContract.View view) {
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
    public void saveArticles(Article article) {
        mInteractor.saveNews(article);
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

    @Override
    public void onReturnSave(Article article) {
        if(mView==null)
            return;
        mView.hideLoading();
        List<Article> articleList=mView.getNewsList();
        articleList.add(article);
        mView.setNewsList(articleList);
        if(articleList.size()==0)
            mView.showEmptyState();
        else{
            mView.hideEmptyState();
            mView.updateView();
        }

    }
}
