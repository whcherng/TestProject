package com.example.hcwong.testproject.Main.News;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.ProgressBar;

import com.example.hcwong.testproject.Http.NewsService;
import com.example.hcwong.testproject.Model.Article;

import java.util.List;

import javax.inject.Inject;

import dagger.Provides;

public class NewsPresenter implements NewsContract.Presenter, NewsInteractor.NewsInteractorCallBack {

    private  Context mContext;
    private NewsContract.View mView;
    public NewsInteractor mInteractor;

    @Inject
    public NewsPresenter(Context mContext, NewsContract.View mView, NewsService newsService) {
        this.mContext = mContext;
        this.mView = mView;
        this.mInteractor = new NewsInteractor(newsService,this);
    }

    @Override
    public void onViewActive(NewsContract.View view) {
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
    public void updateProgress(Integer pb) {
        if(mView==null)
            return;
        mView.updateProgressBar(pb);
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
    public void onUpdate(Integer values) {

    }
}
