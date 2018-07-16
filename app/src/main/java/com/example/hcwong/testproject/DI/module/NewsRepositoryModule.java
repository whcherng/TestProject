package com.example.hcwong.testproject.DI.module;

import android.content.Context;

import com.example.hcwong.testproject.LocalDb.DbHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class NewsRepositoryModule {

    @Singleton
    @Provides
    DbHelper provideLocalDb(Context context){
        return new DbHelper(context);



    }

}
