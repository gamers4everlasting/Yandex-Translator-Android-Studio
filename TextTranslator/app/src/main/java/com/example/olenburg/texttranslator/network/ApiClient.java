package com.example.olenburg.texttranslator.network;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient extends Application {

    public static final String BASE_URL = "https://translate.yandex.net/api/v1.5/tr.json/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient()
    {

        if(retrofit == null)
        {
            retrofit  =  new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
        }
        return retrofit;
    }


}
