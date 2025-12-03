package com.example.animedex;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity {

    private TextView tvTotalHours, tvCompletedAnime;
    private RecyclerView rvAnimeList;
    private DashboardAnimeAdapter adapter;
    private List<Anime> dashboardList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        tvTotalHours = findViewById(R.id.tvTotalHours);
        tvCompletedAnime = findViewById(R.id.tvCompletedAnime);
        rvAnimeList = findViewById(R.id.rvAnimeList);

        dashboardList = new ArrayList<>();
        adapter = new DashboardAnimeAdapter(this, dashboardList);
        rvAnimeList.setAdapter(adapter);
        rvAnimeList.setLayoutManager(new LinearLayoutManager(this));



        int currentUserId = 2;
        loadDashboardDataFromApi(currentUserId);
    }

    private void loadDashboardDataFromApi(int userId) {
        CompletedListRequest request = new CompletedListRequest(userId);

        ApiManager.apiService.getCompletedAnimeList(request).enqueue(new Callback<CompletedListResponse>() {
            @Override
            public void onResponse(Call<CompletedListResponse> call, Response<CompletedListResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    CompletedListResponse res = response.body();

                    dashboardList.clear();
                    if (res.getCompleted() != null) {
                        dashboardList.addAll(res.getCompleted());
                    }
                    adapter.notifyDataSetChanged();

                    int totalEpisodes = res.getTotalEpisodes();
                    double totalHours = totalEpisodes * 24.0 / 60.0;

                    tvTotalHours.setText("Total hours: " + totalHours);
                    tvCompletedAnime.setText("Completed Anime: " + res.getTotalAnime());
                } else {
                    Toast.makeText(DashboardActivity.this, "Failed to load data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CompletedListResponse> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(DashboardActivity.this, "Error connecting to server", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
