package com.example.hcwong.testproject.Http;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebService {
    public static String API_KEY="b95b4854ab3e4348be3e5f53b39f3378";
    public static String BASE_URL="https://newsapi.org/v2/";;

    public static  Retrofit retrofit= new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

}
