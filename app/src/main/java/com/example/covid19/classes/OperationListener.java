package com.example.covid19.classes;

import com.example.covid19.models.Result;

public interface OperationListener {
    void onSuccessed(Result result);
    void onError(Result result);
}
