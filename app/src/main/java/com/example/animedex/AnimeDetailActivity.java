package com.example.animedex;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AnimeDetailActivity extends AppCompatActivity {

    private TextView title, synopsis,score, year;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_anime_detail);

        title = findViewById(R.id.title);
        synopsis = findViewById(R.id.synopsis);
        score = findViewById(R.id.score);
        year = findViewById(R.id.year);
        image = findViewById(R.id.image);

        int animeId = getIntent().getIntExtra("anime_id",-1);
        if (animeId != -1) {
            fetchAnimeDetails(animeId);
        } else {
            Toast.makeText(this, "Invalid anime ID", Toast.LENGTH_SHORT).show();
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void fetchAnimeDetails(int id){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<ApiDetailResponse> call = apiService.getAnimeDetail(id);
        call.enqueue(new Callback<ApiDetailResponse>() {
            @Override
            public void onResponse(Call<ApiDetailResponse> call, Response<ApiDetailResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Anime anime = response.body().data;

                    title.setText(anime.getTitle());
                    synopsis.setText(anime.getSynopsis());
                    score.setText("Score: " + anime.getScore());
                    year.setText("Year: " + anime.getYear());

                    Glide.with(AnimeDetailActivity.this)
                            .load(anime.getImage())
                            .into(image);
                } else {
                    Toast.makeText(AnimeDetailActivity.this, "Failed to load details", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ApiDetailResponse> call, Throwable t) {
                Toast.makeText(AnimeDetailActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}