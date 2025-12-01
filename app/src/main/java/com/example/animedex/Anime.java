package com.example.animedex;

import com.google.gson.annotations.SerializedName;

public class Anime {
    private int id;
    private String title;
    private String image;
    private Double score;

    // Fields added for detail API
    private String synopsis;
    private Integer year;

    private Integer episodes = 0;
    // Default constructor (needed by Gson)
    public Anime() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }

    public String getImage() { return image; }

    public Double getScore() { return score; }

    public String getSynopsis() { return synopsis; }
    public Integer getYear() { return year; }

    public Integer getEpisodes() {
        return episodes;
    }
}
