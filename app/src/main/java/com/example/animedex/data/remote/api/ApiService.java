package com.example.animedex.data.remote.api;

import com.example.animedex.data.remote.api.request.CompletedListRequest;
import com.example.animedex.data.remote.api.request.CompletedAnimeRequest;
import com.example.animedex.data.remote.api.response.CompletedListResponse;
import com.example.animedex.data.remote.api.response.ApiDetailResponse;
import com.example.animedex.data.remote.api.response.ApiCompletedResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    @GET("anime")
    Call<ApiResponse> getAnimeList();
    @GET("anime/{id}")
    Call<ApiDetailResponse> getAnimeDetail(@Path("id") int id);
    @POST("api/completed")
    Call<ApiCompletedResponse> addCompleted(@Body CompletedAnimeRequest request);
    @POST("api/completed/list")
    Call<CompletedListResponse> getCompletedAnimeList(@Body CompletedListRequest request);

}
