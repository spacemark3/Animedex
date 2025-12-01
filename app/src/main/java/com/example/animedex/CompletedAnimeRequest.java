package com.example.animedex;

import com.google.gson.annotations.SerializedName;

public class CompletedAnimeRequest {
    public int user_id;
    public int anime_id;
    public String title;
    public String image_url;
    public Double score;
    public int episodes = 0;

    public CompletedAnimeRequest(int user_id, int anime_id, String title, String image_url, Double score, int episodes) {
        this.user_id = user_id;
        this.anime_id = anime_id;
        this.title = title;
        this.image_url = image_url;
        this.score = score;
        this.episodes = episodes;
    }
}
