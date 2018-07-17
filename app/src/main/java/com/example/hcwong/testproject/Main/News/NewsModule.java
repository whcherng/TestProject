package com.example.hcwong.testproject.Main.News;

import com.example.hcwong.testproject.DI.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class NewsModule{
    private NewsContract.View view;

    public NewsModule(NewsContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    public NewsContract.View provideView(){
        return view;
    }
}
