package com.example.animedex.ui.activities;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.animedex.R;
import com.example.animedex.data.model.Anime;
import com.example.animedex.data.remote.api.response.ApiCompletedResponse;
import com.example.animedex.data.remote.api.response.ApiDetailResponse;
import com.example.animedex.data.remote.api.ApiManager;
import com.example.animedex.data.remote.api.request.CompletedAnimeRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AnimeDetailActivity extends AppCompatActivity {

    private TextView title, synopsis,score, year, episodes;
    private ImageView image;
    private Anime currentAnime;
    private int userId = 2;

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
        episodes = findViewById(R.id.episodes);
        Button btnAdd = findViewById(R.id.btnAddCompleted);

        int animeId = getIntent().getIntExtra("anime_id",-1);
        if (animeId != -1) {
            fetchAnimeDetails(animeId);
        } else {
            Toast.makeText(this, "Invalid anime ID", Toast.LENGTH_SHORT).show();
        }

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAnimeToCompleted();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_detail), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void fetchAnimeDetails(int id){
        ApiManager.apiService.getAnimeDetail(id).enqueue(new Callback<ApiDetailResponse>() {
            @Override
            public void onResponse(Call<ApiDetailResponse> call, Response<ApiDetailResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    currentAnime = response.body().data;

                    title.setText(currentAnime.getTitle());
                    synopsis.setText(currentAnime.getSynopsis());
                    score.setText("Score: " + currentAnime.getScore());
                    year.setText("Year: " + currentAnime.getYear());
                    episodes.setText("Episodes:" + currentAnime.getEpisodes());

                    Glide.with(AnimeDetailActivity.this)
                            .load(currentAnime.getImage())
                            .into(image);
                } else {
                    Toast.makeText(AnimeDetailActivity.this, "Failed to load details", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ApiDetailResponse> call, Throwable t) {Toast.makeText(AnimeDetailActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    private void addAnimeToCompleted() {
        if (currentAnime == null) {
            Toast.makeText(this, "Anime not loaded yet", Toast.LENGTH_SHORT).show();
            return;
        }
        CompletedAnimeRequest request = new CompletedAnimeRequest(
                userId,
                currentAnime.getId(),
                currentAnime.getTitle(),
                currentAnime.getImage(),
                currentAnime.getScore(),
                currentAnime.getEpisodes()
        );

        ApiManager.apiService.addCompleted(request).enqueue(new Callback<ApiCompletedResponse>() {
            @Override
            public void onResponse(Call<ApiCompletedResponse> call, Response<ApiCompletedResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(AnimeDetailActivity.this, "Added to completed!", Toast.LENGTH_LONG).show();
                } else if (response.code() == 409) {
                    Toast.makeText(AnimeDetailActivity.this, "This anime is already in your completed list", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(AnimeDetailActivity.this, "Error adding anime", Toast.LENGTH_LONG).show();System.out.println("error" + response.message());
                }
            }
            @Override
            public void onFailure(Call<ApiCompletedResponse> call, Throwable t) {
                Toast.makeText(AnimeDetailActivity.this, "Server error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}