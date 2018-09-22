package com.example.olenburg.texttranslator.network;

import com.example.olenburg.texttranslator.models.Translate;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiServices {
    @GET("translate?")
    Call<Translate> getAllPosts(@Query("key") String key, @Query("text") String text, @Query("lang") String lang);

}
