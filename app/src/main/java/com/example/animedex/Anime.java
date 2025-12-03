package com.example.animedex;

import com.google.gson.annotations.SerializedName;

public class Anime {
    private int id;
    private String title;

    private String image;

    private Double score;
    private String synopsis;
    private Integer year;
    private Integer episodes;

    public Anime() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }  // Fixed!

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }  // Fixed!

    public Double getScore() { return score; }
    public void setScore(Double score) { this.score = score; }

    public String getSynopsis() { return synopsis; }
    public void setSynopsis(String synopsis) { this.synopsis = synopsis; }

    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }

    public Integer getEpisodes() { return episodes; }
    public void setEpisodes(Integer episodes) { this.episodes = episodes; }
}