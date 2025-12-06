package com.example.animedex.data.remote.api.request;
public class CompletedAnimeRequest {
    public int user_id;
    public int anime_id;
    public String title;
    public String image;
    public Double score;
    public Integer episodes;

    public CompletedAnimeRequest(int user_id, int anime_id, String title, String image, Double score, Integer episodes) {
        this.user_id = user_id;
        this.anime_id = anime_id;
        this.title = title;
        this.image= image;
        this.score = score;
        this.episodes = episodes;
    }
}
