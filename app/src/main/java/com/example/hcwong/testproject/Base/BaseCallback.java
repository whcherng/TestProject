package com.example.hcwong.testproject.Base;

public interface BaseCallback<T> extends IBaseCallback<T>{
    void onFailure(Throwable throwable, String response);
    void onNetworkFailure();
}
