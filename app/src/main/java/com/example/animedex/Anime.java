package com.example.animedex;
public class Anime {

    private int id;
    private String title;
    private String image;
    private Double score;
    public Anime() {}
    public Anime(int id, String title, String image, Double score) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.score = score;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public Double getScore() { return score; }
    public void setScore(Double score) { this.score = score; }
}
