package com.example.covid19.models;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofits {
    private static Retrofits retrofits;
    private static Retrofit retrofit;

    public static synchronized Retrofits getInstence(){
        if(retrofits == null){
            try {
                retrofits = new Retrofits();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return retrofits;
    }

    private Retrofits(){
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }


}
