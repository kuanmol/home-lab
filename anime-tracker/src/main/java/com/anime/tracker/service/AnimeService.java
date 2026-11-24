package com.anime.tracker.service;

import com.anime.tracker.model.Anime;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnimeService {

    // Temporary in-memory storage
    private final List<Anime> animeList = new ArrayList<>();

    // Automatically generate IDs
    private Long nextId = 1L;

    // CREATE
    public Anime addAnime(Anime anime) {
        anime.setId(nextId++);
        animeList.add(anime);
        return anime;
    }

    // READ ALL
    public List<Anime> getAllAnime() {
        return animeList;
    }

    // READ BY ID
    public Anime getAnimeById(Long id) {
        return animeList.stream()
                .filter(anime -> anime.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // UPDATE
    public Anime updateAnime(Long id, Anime updatedAnime) {

        Anime existingAnime = getAnimeById(id);

        if (existingAnime == null) {
            return null;
        }

        existingAnime.setTitle(updatedAnime.getTitle());
        existingAnime.setGenre(updatedAnime.getGenre());
        existingAnime.setEpisodes(updatedAnime.getEpisodes());
        existingAnime.setRating(updatedAnime.getRating());
        existingAnime.setStatus(updatedAnime.getStatus());

        return existingAnime;
    }

    // DELETE
    public boolean deleteAnime(Long id) {
        return animeList.removeIf(anime -> anime.getId().equals(id));
    }
}
