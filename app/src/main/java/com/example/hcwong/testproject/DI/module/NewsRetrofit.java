package com.example.hcwong.testproject.DI.module;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
public class NewsRetrofit {
    public static String API_KEY="b95b4854ab3e4348be3e5f53b39f3378";
    public static String BASE_URL="https://newsapi.org/v2/";
    private static Retrofit retrofit;

    @Inject
    public NewsRetrofit(){
       retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Retrofit getRetrofit(){
        return retrofit;
    }
}
