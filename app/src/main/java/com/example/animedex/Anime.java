package com.example.animedex;
public class Anime {
    private int id;
    private String title;
    private String image;
    private Double score;

    // Fields added for detail API
    private String synopsis;
    private Integer year;
    private String status;
    // Default constructor (needed by Gson)
    public Anime() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }

    public String getImage() { return image; }

    public Double getScore() { return score; }

    public String getSynopsis() { return synopsis; }
    public void setSynopsis(String synopsis) { this.synopsis = synopsis; }

    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }
}
