package com.example.hcwong.testproject.Main.News;

import android.content.Context;
import android.os.AsyncTask;

import com.example.hcwong.testproject.Http.NewsService;
import com.example.hcwong.testproject.Http.WebService;
import com.example.hcwong.testproject.Model.Article;
import com.example.hcwong.testproject.Model.News;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import retrofit2.Call;

import static com.example.hcwong.testproject.DI.module.NewsRetrofit.API_KEY;

public class NewsInteractor {
    private GetNewsTask getNewsTask;
    private NewsInteractorCallBack mCallBack;
    private NewsService newsService;

    @Inject
    public NewsInteractor( NewsService newsService,NewsInteractorCallBack mCallBack) {
        this.newsService=newsService;
        this.mCallBack = mCallBack;
    }

    public interface NewsInteractorCallBack {
        void onReturnNews(List<Article> articleList);
        void onUpdate(Integer values);
    }

    public void getNews(){
        getNewsTask = new GetNewsTask();
        getNewsTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private class GetNewsTask extends AsyncTask<Void, Integer,List<Article>>{


        @Override
        protected List<Article> doInBackground(Void... voids) {
            List<Article> articles=new ArrayList<>();
            Call<News> news=newsService.listNews("us",API_KEY);
            try {
                articles=news.execute().body().getArticles();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return articles;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            if(mCallBack!=null)
                mCallBack.onUpdate(values[0]);
        }

        @Override
        protected void onPostExecute(List<Article> articleList) {
            super.onPostExecute(articleList);
            if(mCallBack!=null)
                mCallBack.onReturnNews(articleList);
        }
    }


}
