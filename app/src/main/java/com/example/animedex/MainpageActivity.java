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

        // -------------------------------
        // RecyclerView setup
        // -------------------------------
        recyclerView = findViewById(R.id.animeRecyclerView); // ID from your XML

        // Horizontal LinearLayoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false
        );
        recyclerView.setLayoutManager(layoutManager);

        // Adapter with empty list first
        adapter = new AnimeAdapter(this, animeList);
        recyclerView.setAdapter(adapter);

        fetchAnimeFromApi();
    }

    private void fetchAnimeFromApi() {
        // 1. Create Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/") // Use your Node server URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // 2. Create API service
        ApiService apiService = retrofit.create(ApiService.class);

        // 3. Make API call
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
            Toast.makeText(this, "Profile",Toast.LENGTH_LONG).show();
        /*Intent profileIntent = new Intent(MainpageActivity.this, ProfileActivity.class);
        startActivity(profileIntent);*/
        }
        if(idItem == R.id.MENU_2) {
            Toast.makeText(this, "Dashboard", Toast.LENGTH_SHORT).show();
            Intent dashboardIntent = new Intent(MainpageActivity.this, DashboardActivity.class);
            startActivity(dashboardIntent);
        }
        if(idItem == R.id.MENU_3) {
            Toast.makeText(this, "Forum", Toast.LENGTH_SHORT).show();
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