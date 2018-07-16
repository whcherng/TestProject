package com.example.hcwong.testproject.DI.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private final Context mContext;

    public AppModule(Context context){
        mContext=context;
    }

    @Singleton
    @Provides
    Context  provideContext(){
        return mContext;
    }

    @Singleton
    @Provides
    public SharedPreferences provideDefaultSharedPreferences()
    {
        return PreferenceManager.getDefaultSharedPreferences(mContext);
    }
}
