package com.example.covid19.models;

import java.io.Serializable;

public class Result<T> implements Serializable {

    private Result(){}

    @Override
    public String toString() {
        return super.toString();
    }

    public final static class Success<T> extends Result{
        private T data;

        public Success(T data){this.data = data;}

        public T getData() {
            return data;
        }
    }

    public final static class Error extends Result{
        private Exception error;

        public Error(Exception error){this.error = error;}
        public Exception getError(){return error;}

    }

}
