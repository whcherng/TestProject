package com.example.hcwong.testproject.Http;

import com.example.hcwong.testproject.Model.News;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsService {
    @GET("top-Headlines")
    Call<News> listNews(@Query("country") String Country, @Query("apiKey") String apiKey);
}
