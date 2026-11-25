
package com.anime.tracker.service;

import com.anime.tracker.model.Anime;
import com.anime.tracker.exception.AnimeNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimeServiceTest {

    private AnimeService animeService;

    @BeforeEach
    void setUp() {
        animeService = new AnimeService();
    }

    @Test
    void shouldAddAnime() {

        Anime anime = new Anime(
                null, "Naruto", "Action", 220, 8.5, "Watching"
        );

        Anime savedAnime = animeService.addAnime(anime);

        assertNotNull(savedAnime.getId());
        assertEquals("Naruto", savedAnime.getTitle());
    }

    @Test
    void shouldGetAnimeById() {

        Anime anime = new Anime(
                null, "One Piece", "Adventure", 1000, 9.0, "Watching"
        );

        Anime savedAnime = animeService.addAnime(anime);

        Anime result = animeService.getAnimeById(savedAnime.getId());

        assertEquals("One Piece", result.getTitle());
    }

    @Test
    void shouldThrowExceptionWhenAnimeNotFound() {

        assertThrows(
                AnimeNotFoundException.class,
                () -> animeService.getAnimeById(999L)
        );
    }

    @Test
    void shouldUpdateAnime() {

        Anime anime = new Anime(
                null, "Naruto", "Action", 220, 8.5, "Watching"
        );

        Anime savedAnime = animeService.addAnime(anime);

        Anime updatedAnime = new Anime(
                null, "Naruto Shippuden", "Action",
                500, 9.0, "Completed"
        );

        Anime result = animeService.updateAnime(
                savedAnime.getId(), updatedAnime
        );

        assertEquals("Naruto Shippuden", result.getTitle());
        assertEquals(500, result.getEpisodes());
    }

    @Test
    void shouldDeleteAnime() {

        Anime anime = new Anime(
                null, "Death Note", "Thriller", 37, 9.0, "Completed"
        );

        Anime savedAnime = animeService.addAnime(anime);

        animeService.deleteAnime(savedAnime.getId());

        assertThrows(
                AnimeNotFoundException.class,
                () -> animeService.getAnimeById(savedAnime.getId())
        );
    }
}
