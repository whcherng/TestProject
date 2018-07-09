package com.example.hcwong.testproject.Base;

public interface BasePresenter<ViewT> {
    void onViewActive(ViewT view);

    void onViewInactive();

    void start();
}
