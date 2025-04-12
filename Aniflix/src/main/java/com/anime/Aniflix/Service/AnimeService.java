package com.anime.Aniflix.Service;

import com.anime.Aniflix.Model.Anime;
import com.anime.Aniflix.Model.AnimeFull;
import com.anime.Aniflix.Model.Episode;

import java.util.List;

public interface AnimeService {
    List<Anime> getTopAnime(int page, int limit); // Get Top Anime with pagination
    List<Anime> getLatestAnime(int page, int limit); // Get Latest Anime with pagination
    List<Anime> searchAnime(String query, int page, int limit); // Search for anime
    AnimeFull getAnimeFullDetails(int malId); // Get full details of an anime
    List<Episode> getAnimeEpisodes(int malId); // Get list of episodes of an anime
}
