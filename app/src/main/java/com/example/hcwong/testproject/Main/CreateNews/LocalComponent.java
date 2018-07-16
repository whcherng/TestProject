package com.example.hcwong.testproject.Main.CreateNews;

import com.example.hcwong.testproject.DI.scope.ActivityScope;
import com.example.hcwong.testproject.Main.News.NewsFragment;
import com.example.hcwong.testproject.Main.News.NewsModule;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = LocalModule.class)
public interface LocalComponent {
    void inject(LocalFragment localFragment);
}
