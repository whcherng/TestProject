package com.example.hcwong.testproject;

import android.app.Application;
import android.content.Context;

import com.example.hcwong.testproject.DI.component.AppComponent;
import com.example.hcwong.testproject.DI.component.DaggerAppComponent;
import com.example.hcwong.testproject.DI.module.AppModule;
import com.example.hcwong.testproject.DI.module.NewsServiceModule;
import com.example.hcwong.testproject.DI.module.RetrofitModule;

public class NewsApplication extends Application{
    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static NewsApplication get(Context context){
        return (NewsApplication) context.getApplicationContext();
    }

    private void setupApplicationComponent(){
        appComponent= DaggerAppComponent.builder()//.newsServiceModule(new NewsServiceModule())
                //.retrofitModule(new RetrofitModule())
                .appModule(new AppModule(this))
                .newsServiceModule(new NewsServiceModule())
                // if module shows that it is deprecated: no attribute within the module is used.
                .build();
    }

    public  AppComponent getAppComponent() {
        if(appComponent==null)
            this.setupApplicationComponent();
        return appComponent;
    }

}
