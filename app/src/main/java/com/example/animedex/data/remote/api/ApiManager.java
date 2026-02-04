package com.example.animedex.data.remote.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {

    private static final String URL = "http://10.0.2.2:3000/";
    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    public static final ApiService apiService = retrofit.create(ApiService.class);

    public String getUrl(){
        return URL;
    }
}