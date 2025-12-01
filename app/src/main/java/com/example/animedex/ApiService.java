package com.example.animedex;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("anime")
    Call<ApiResponse> getAnimeList();
    @GET("anime/{id}")
    Call<ApiDetailResponse> getAnimeDetail(@Path("id") int id);
}
