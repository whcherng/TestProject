package com.example.hcwong.testproject.NewsDetails;

import com.example.hcwong.testproject.Base.BasePresenter;
import com.example.hcwong.testproject.Base.BaseView;

public interface NewsDetailsContract {
    interface View extends BaseView<NewsDetailsContract.Presenter> {
        void initView();
        void renderView();
        void updateView();
        void initListener();
        void showEmptyState();
        void hideEmptyState();


    }

    interface Presenter extends BasePresenter<NewsDetailsContract.View> {
        void getNews();
        void shareNews(String news);
    }
}
