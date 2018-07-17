package com.example.hcwong.testproject.Base;

public interface BasePresenter<V extends BaseView> {
    void onViewActive(V view);

    void onViewInactive();

    void start();
}
