package com.example.hcwong.testproject.Base;

public interface BaseView<T extends BasePresenter> extends IBaseView<T> {
    void showLoading();

    void hideLoading();

    void hideKeyboard();

    void showToastMessage(int resId);
}
