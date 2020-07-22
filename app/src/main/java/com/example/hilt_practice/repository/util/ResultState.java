package com.example.hilt_practice.repository.util;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

final public class ResultState<T> {

    private final T _result;
    private final Throwable _error;

    public ResultState(@Nullable T result, @Nullable Throwable error){
        this._result = result;
        this._error = error;
    }

    public static <T> ResultState<T> success(@Nonnull T result){
        return new ResultState<>(result, null);
    }

    public static <T> ResultState<T> error(@Nonnull Throwable error){
        return new ResultState<>(null, error);
    }

    public T get_result(){
        return _result;
    }

    public Throwable get_error(){
        return _error;
    }
}
