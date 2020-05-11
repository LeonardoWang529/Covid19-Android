package com.example.covid19.models;

import okhttp3.OkHttpClient;

public class OKhttpConnect {
    static OKhttpConnect oKhttpConnect;
    public OkHttpClient okHttpClient;

    private OKhttpConnect(){
        okHttpClient = new OkHttpClient();
    }

    public static synchronized OKhttpConnect getInstence(){
        if(oKhttpConnect == null){
            try {
                oKhttpConnect = new OKhttpConnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return oKhttpConnect;
    }

}
