package com.anime.tracker.exception;

public class AnimeNotFoundException extends RuntimeException {

    public AnimeNotFoundException(Long id) {
        super("Anime not found with ID: " + id);
    }
}
