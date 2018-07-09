package com.example.hcwong.testproject.Main.News;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.hcwong.testproject.Http.NewsService;
import com.example.hcwong.testproject.Http.WebService;
import com.example.hcwong.testproject.Model.Article;
import com.example.hcwong.testproject.Model.News;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;


public class NewsInteractor {
    private Context mContext;
    private GetNewsTask getNewsTask;
    private NewsInteractorCallBack mCallBack;


    public NewsInteractor(Context mContext, NewsInteractorCallBack mCallBack) {
        this.mContext = mContext;
        this.mCallBack = mCallBack;
    }


//
//    public List<Article> getNews(){
//
//        NewsService service= WebService.retrofit.create(NewsService.class);
//        Call<News> news=service.listNews("us",WebService.API_KEY);
//        news.enqueue(new Callback<News>() {
//            @Override
//            public void onResponse(Call<News> call, Response<News> response) {
//                List<Article> listOfArticles =response.body().getArticles();
//
//            }
//            @Override
//            public void onFailure(Call<News> call, Throwable t) {
//                Log.e("Testing", t.toString());
//            }
//        });
//
//    }

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
            NewsService service= WebService.retrofit.create(NewsService.class);
            Call<News> news=service.listNews("us",WebService.API_KEY);
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
