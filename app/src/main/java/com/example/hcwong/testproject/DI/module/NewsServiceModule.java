package com.example.hcwong.testproject.DI.module;

import com.example.hcwong.testproject.Http.NewsService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class NewsServiceModule {
    @Singleton
    @Provides
    public NewsService provideUserSerivce(NewsRetrofit retrofit){
        return retrofit.getRetrofit().create(NewsService.class);
    }
}
