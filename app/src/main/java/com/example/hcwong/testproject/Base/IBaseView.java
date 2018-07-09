package com.example.hcwong.testproject.Base;

public interface IBaseView<T extends BasePresenter> {
    void setPresenter(T presenter);
}
