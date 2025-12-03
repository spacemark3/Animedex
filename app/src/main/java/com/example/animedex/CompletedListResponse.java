package com.example.animedex;

import java.util.List;

public class CompletedListResponse {
    private String message;
    private int totalEpisodes;
    private int totalAnime;
    private List<Anime> completed;

    public String getMessage() { return message; }
    public int getTotalEpisodes() { return totalEpisodes; }
    public int getTotalAnime() { return totalAnime; }
    public List<Anime> getCompleted() { return completed; }
}
