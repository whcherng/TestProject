package com.example.hcwong.testproject.DI.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RetrofitModule {
    @Singleton
    @Provides
    public NewsRetrofit provideRemoteRetrofit(){
        return new NewsRetrofit();
    }



}
