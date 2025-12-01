package com.example.animedex;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainpageActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AnimeAdapter adapter;
    private List<Anime> animeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mainpage);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        recyclerView = findViewById(R.id.animeRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new AnimeAdapter(this, animeList, anime -> {
            Intent intent = new Intent(MainpageActivity.this, AnimeDetailActivity.class);
            intent.putExtra("anime_id", anime.getId());
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);

        fetchAnimeFromApi();
    }

    private void fetchAnimeFromApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);

        Call<ApiResponse> call = apiService.getAnimeList();
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    animeList.clear();
                    animeList.addAll(response.body().data);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(MainpageActivity.this, "Anime loaded!", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("API", "Response code: " + response.code() + " body: " + response.errorBody());
                    Toast.makeText(MainpageActivity.this, "Failed to load anime", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.e("API", "Retrofit call failed", t);
                Toast.makeText(MainpageActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int idItem = item.getItemId();
        if(idItem == R.id.MENU_1){
            Toast.makeText(this, "Profile in progress!",Toast.LENGTH_LONG).show();
        /*Intent profileIntent = new Intent(MainpageActivity.this, ProfileActivity.class);
        startActivity(profileIntent);*/
        }
        if(idItem == R.id.MENU_2) {
            Toast.makeText(this, "Dashboard", Toast.LENGTH_SHORT).show();
            Intent dashboardIntent = new Intent(MainpageActivity.this, DashboardActivity.class);
            startActivity(dashboardIntent);
        }
        if(idItem == R.id.MENU_3) {
            Toast.makeText(this, "Forum in progress!", Toast.LENGTH_SHORT).show();
        /*Intent forumIntent = new Intent(MainpageActivity.this, ForumActivity.class);
        startActivity(forumIntent);*/
        }
        if(idItem == R.id.MENU_4) {
            Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
            Intent logoutIntent = new Intent(MainpageActivity.this, LoginActivity.class);
            startActivity(logoutIntent);
        }
        return true;
    }
}