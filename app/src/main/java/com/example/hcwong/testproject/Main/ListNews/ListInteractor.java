package com.example.hcwong.testproject.Main.ListNews;

import android.content.Context;
import android.os.AsyncTask;

import com.example.hcwong.testproject.Http.NewsService;
import com.example.hcwong.testproject.Http.WebService;
import com.example.hcwong.testproject.Model.Article;
import com.example.hcwong.testproject.Model.News;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;


public class ListInteractor {
    private Context mContext;
    private GetNewsTask getNewsTask;
    private ListInteractorCallBack mCallBack;


    public ListInteractor(Context mContext, ListInteractorCallBack mCallBack) {
        this.mContext = mContext;
        this.mCallBack = mCallBack;
    }

    public interface ListInteractorCallBack {
        void onReturnNews(List<Article> articleList);
    }

    public void getNews(){
        getNewsTask = new GetNewsTask();
        getNewsTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }


    private class GetNewsTask extends AsyncTask<Void, List<Article>,List<Article>>{


        @Override
        protected List<Article> doInBackground(Void... voids) {
            List<Article> articles=new ArrayList<>();
            NewsService service= WebService.retrofit.create(NewsService.class);
            Call<News> news=service.listNews("us",WebService.API_KEY);
            try {
                articles=news.execute().body().getArticles();
                for(Article article : articles){
                    if(article.getDescription()==null)
                        article.setDescription("");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return articles;
        }

        @Override
        protected void onPostExecute(List<Article> articleList) {
            super.onPostExecute(articleList);
            if(mCallBack!=null)
                mCallBack.onReturnNews(articleList);
        }
    }


}
