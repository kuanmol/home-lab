
package com.anime.tracker.model;

public class Anime {

    private Long id;
    private String title;
    private String genre;
    private int episodes;
    private double rating;
    private String status;

    // Default constructor
    public Anime() {
    }

    // Parameterized constructor
    public Anime(Long id, String title, String genre,
                 int episodes, double rating, String status) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.episodes = episodes;
        this.rating = rating;
        this.status = status;
    }

    // Getters and Setters

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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
