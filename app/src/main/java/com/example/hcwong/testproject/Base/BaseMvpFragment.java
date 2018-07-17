package com.example.hcwong.testproject.Base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hcwong.testproject.DI.component.AppComponent;
import com.example.hcwong.testproject.DI.component.DaggerAppComponent;
import com.example.hcwong.testproject.Main.News.NewsModule;
import com.example.hcwong.testproject.NewsApplication;

import javax.inject.Inject;

public abstract class BaseMvpFragment<P extends BasePresenter> extends Fragment{
    @Inject
    protected P mvpPresenter;
    protected abstract void initInject(AppComponent appComponent);
}
