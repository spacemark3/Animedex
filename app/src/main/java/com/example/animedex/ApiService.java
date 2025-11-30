package com.example.animedex;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("anime") // Your endpoint from Node Express (e.g., http://10.0.2.2:3000/)
    Call<ApiResponse> getAnimeList();
}
