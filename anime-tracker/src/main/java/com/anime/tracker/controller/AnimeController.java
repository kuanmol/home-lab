
package com.anime.tracker.controller;

import com.anime.tracker.model.Anime;
import com.anime.tracker.service.AnimeService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/anime")
public class AnimeController {

    private final AnimeService animeService;

    // Constructor injection
    public AnimeController(AnimeService animeService) {
        this.animeService = animeService;
    }

    // GET ALL ANIME
    @GetMapping
    public List<Anime> getAllAnime() {
        return animeService.getAllAnime();
    }

    // GET ANIME BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Anime> getAnimeById(@PathVariable Long id) {
        return ResponseEntity.ok(animeService.getAnimeById(id));
    }

    // ADD ANIME
    @PostMapping
    public ResponseEntity<Anime> addAnime(@Valid @RequestBody Anime anime) {
        Anime createdAnime = animeService.addAnime(anime);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createdAnime);
    }

    // UPDATE ANIME
    @PutMapping("/{id}")
    public ResponseEntity<Anime> updateAnime(@PathVariable Long id, @Valid @RequestBody Anime anime) {
        return ResponseEntity.ok(animeService.updateAnime(id, anime));
    }

    // DELETE ANIME
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnime(@PathVariable Long id) {
        animeService.deleteAnime(id);
        return ResponseEntity.noContent().build();
    }
}
