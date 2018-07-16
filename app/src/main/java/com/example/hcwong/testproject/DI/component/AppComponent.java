package com.example.hcwong.testproject.DI.component;

import com.example.hcwong.testproject.DI.module.AppModule;
import com.example.hcwong.testproject.DI.module.NewsServiceModule;
import com.example.hcwong.testproject.DI.module.RetrofitModule;
import com.example.hcwong.testproject.Main.CreateNews.LocalComponent;
import com.example.hcwong.testproject.Main.CreateNews.LocalModule;
import com.example.hcwong.testproject.Main.News.NewsComponent;
import com.example.hcwong.testproject.Main.News.NewsModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules ={AppModule.class,
        RetrofitModule.class,
        NewsServiceModule.class})
public interface AppComponent {
    NewsComponent addSub(NewsModule newsModule);
    LocalComponent addSub(LocalModule localModule);
}
