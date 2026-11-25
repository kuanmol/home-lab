package com.anime.tracker.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class Anime {

    private Long id;

    @NotBlank(message = "Anime title cannot be empty")
    @Size(max = 100, message = "Anime title cannot exceed 100 characters")
    private String title;

    @NotBlank(message = "Genre cannot be empty")
    private String genre;

    @PositiveOrZero(message = "Episodes cannot be negative")
    private int episodes;

    @NotNull(message = "Rating is required")
    @Min(value = 0, message = "Rating must be at least 0")
    @Max(value = 10, message = "Rating cannot exceed 10")
    private Double rating;

    @NotBlank(message = "Status cannot be empty")
    private String status;

    public Anime() {
    }

    public Anime(Long id, String title, String genre, int episodes, Double rating, String status) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.episodes = episodes;
        this.rating = rating;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getEpisodes() {
        return episodes;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
