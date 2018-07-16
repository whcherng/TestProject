package com.example.hcwong.testproject.Main.CreateNews;

import com.example.hcwong.testproject.DI.scope.ActivityScope;
import com.example.hcwong.testproject.Main.News.NewsContract;

import dagger.Module;
import dagger.Provides;

@Module
public class LocalModule {
    private LocalContract.View view;

    public LocalModule(LocalContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    public LocalContract.View provideView(){
        return view;
    }

}
