package com.anime.Aniflix.Controller;

import com.anime.Aniflix.Model.Anime;
import com.anime.Aniflix.Model.AnimeFull;
import com.anime.Aniflix.Model.Episode;
import com.anime.Aniflix.Service.AnimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AnimeController {

    @Autowired
    private AnimeService animeService;

    // Show top anime on home page with pagination
    @GetMapping("/")
    public String showHomePage(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "10") int limit,
            Model model) {

        List<Anime> topAnime = animeService.getTopAnime(page, limit);
        List<Anime> latestAnime = animeService.getLatestAnime(page, limit);

        model.addAttribute("topAnime", topAnime);
        model.addAttribute("latestAnime", latestAnime);
        return "index"; // points to index.html (Thymeleaf)
    }

    // Show anime details by ID
    @GetMapping("/anime/{id}")
    public String getAnimeDetails(@PathVariable int id, Model model) {
        AnimeFull anime = animeService.getAnimeFullDetails(id);
        List<Episode> episodes = animeService.getAnimeEpisodes(id);

        model.addAttribute("anime", anime);
        model.addAttribute("episodes", episodes);
        return "anime-details"; // anime-details.html
    }

    // Search anime by query with pagination
    @GetMapping("/search")
    public String searchAnime(
            @RequestParam("query") String query,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "10") int limit,
            Model model) {

        List<Anime> results = animeService.searchAnime(query, page, limit);

        model.addAttribute("results", results);
        model.addAttribute("query", query);
        return "search-results"; // search-results.html
    }
}
