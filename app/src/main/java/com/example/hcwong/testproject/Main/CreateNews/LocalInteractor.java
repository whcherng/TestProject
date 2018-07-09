package com.example.hcwong.testproject.Main.CreateNews;

import android.content.Context;
import android.os.AsyncTask;

import com.example.hcwong.testproject.Http.NewsService;
import com.example.hcwong.testproject.Http.WebService;
import com.example.hcwong.testproject.LocalDb.DbHelper;
import com.example.hcwong.testproject.Model.Article;
import com.example.hcwong.testproject.Model.News;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;


public class LocalInteractor {
    private Context mContext;
    private DbHelper myDb;
    private GetNewsTask getNewsTask;
    private NewsInteractorCallBack mCallBack;
    private SaveArticleTask saveArticleTask;


    public LocalInteractor(Context mContext, NewsInteractorCallBack mCallBack) {
        this.mContext = mContext;
        this.mCallBack = mCallBack;
        myDb= new DbHelper(mContext);
    }

    public interface NewsInteractorCallBack {
        void onReturnNews(List<Article> articleList);
        void onReturnSave(Article articleList);
    }

    public void getNews(){
        getNewsTask = new GetNewsTask();
        getNewsTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void saveNews(Article article){
        saveArticleTask = new SaveArticleTask(article);
        saveArticleTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }


    private class GetNewsTask extends AsyncTask<Void, List<Article>,List<Article>>{


        @Override
        protected List<Article> doInBackground(Void... voids) {
            List<Article> articles=myDb.getAllArticle();
            return articles;
        }

        @Override
        protected void onPostExecute(List<Article> articleList) {
            super.onPostExecute(articleList);
            if(mCallBack!=null)
                mCallBack.onReturnNews(articleList);
        }
    }

    private class SaveArticleTask extends AsyncTask<Void, Article,Article>{
        Article article;

        public SaveArticleTask(Article article) {
            this.article=article;
        }

        @Override
        protected Article doInBackground(Void... voids) {
            Article articles=myDb.insertArticle(article);
            return articles;
        }

        @Override
        protected void onPostExecute(Article articleList) {
            super.onPostExecute(articleList);
            if(mCallBack!=null)
                mCallBack.onReturnSave(articleList);
        }
    }


}
