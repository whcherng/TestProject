package com.example.hcwong.testproject.Base;

import android.support.v4.app.Fragment;

import com.example.hcwong.testproject.DI.component.AppComponent;

import javax.inject.Inject;

public abstract class BaseMvpFragment<P extends BasePresenter> extends Fragment {
    @Inject
    protected P mvpPresenter;

    protected abstract void initInject(AppComponent appComponent);
}
