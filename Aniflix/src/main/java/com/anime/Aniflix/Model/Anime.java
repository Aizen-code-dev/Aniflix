package com.anime.Aniflix.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

// Anime.java
public class Anime {
    private int mal_id;
    private String title;
    private String synopsis;
    private String status;
    private String type;
    private int episodes;
    private double score;
    private Map<String, ImageInfo> images;

    public int getMal_id() {
        return mal_id;
    }

    public void setMal_id(int mal_id) {
        this.mal_id = mal_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getEpisodes() {
        return episodes;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Map<String, ImageInfo> getImages() {
        return images;
    }

    public void setImages(Map<String, ImageInfo> images) {
        this.images = images;
    }

    public String getImageUrl() {
        return images != null && images.get("jpg") != null ? images.get("jpg").getImage_url() : null;
    }
}
