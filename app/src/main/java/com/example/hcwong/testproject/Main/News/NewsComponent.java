package com.example.hcwong.testproject.Main.News;

import com.example.hcwong.testproject.DI.scope.ActivityScope;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = NewsModule.class)
public interface NewsComponent {
    void inject(NewsFragment newsFragment);
}
