package com.example.hcwong.testproject.Main.News;

import android.widget.ProgressBar;

import com.example.hcwong.testproject.Base.BasePresenter;
import com.example.hcwong.testproject.Base.BaseView;
import com.example.hcwong.testproject.Model.Article;

import java.util.List;

public interface NewsContract {
    interface View extends BaseView<Presenter>{
        void initView();
        void renderView();
        void updateView();
        void initListener();
        void showEmptyState();
        void hideEmptyState();
        void updateProgressBar(Integer pb);

        void setNewsList(List<Article> newsList);
        List<Article> getNewsList();
    }

    interface Presenter extends BasePresenter<View>{
        void getNews();
        void updateProgress(Integer pb);
    }
}
