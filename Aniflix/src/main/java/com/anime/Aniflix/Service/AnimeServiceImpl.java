package com.anime.Aniflix.Service;

import com.anime.Aniflix.Model.Anime;
import com.anime.Aniflix.Model.AnimeFull;
import com.anime.Aniflix.Model.Episode;
import com.anime.Aniflix.Model.JikanResponse;
import com.anime.Aniflix.Model.AnimeFullResponse;
import com.anime.Aniflix.Model.EpisodeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

@Service
public class AnimeServiceImpl implements AnimeService {

    private final RestTemplate restTemplate;

    @Autowired
    public AnimeServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Anime> getTopAnime(int page, int limit) {
        String url = "https://api.jikan.moe/v4/top/anime?order_by=score&limit=" + limit + "&page=" + page;
        try {
            ResponseEntity<JikanResponse> response = restTemplate.getForEntity(url, JikanResponse.class);
            if (response != null && response.getBody() != null) {
                return response.getBody().getData();
            } else {
                return Collections.emptyList(); // Return an empty list if no data is found
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList(); // Return an empty list in case of error
        }
    }

    @Override
    public List<Anime> getLatestAnime(int page, int limit) {
        String url = "https://api.jikan.moe/v4/anime?order_by=start_date&limit=" + limit + "&page=" + page;
        try {
            ResponseEntity<JikanResponse> response = restTemplate.getForEntity(url, JikanResponse.class);
            if (response != null && response.getBody() != null) {
                return response.getBody().getData();
            } else {
                return Collections.emptyList();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<Anime> searchAnime(String query, int page, int limit) {
        String url = "https://api.jikan.moe/v4/anime?q=" + query + "&limit=" + limit + "&page=" + page;
        try {
            ResponseEntity<JikanResponse> response = restTemplate.getForEntity(url, JikanResponse.class);
            if (response != null && response.getBody() != null) {
                return response.getBody().getData();
            } else {
                return Collections.emptyList();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public AnimeFull getAnimeFullDetails(int malId) {
        String url = "https://api.jikan.moe/v4/anime/" + malId;
        try {
            ResponseEntity<AnimeFullResponse> response = restTemplate.getForEntity(url, AnimeFullResponse.class);
            if (response != null && response.getBody() != null) {
                return response.getBody().getData();
            } else {
                return null; // Return null if no data is found
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Episode> getAnimeEpisodes(int malId) {
        String url = "https://api.jikan.moe/v4/anime/" + malId + "/episodes";
        try {
            ResponseEntity<EpisodeResponse> response = restTemplate.getForEntity(url, EpisodeResponse.class);
            if (response != null && response.getBody() != null) {
                return response.getBody().getData();
            } else {
                return Collections.emptyList(); // Return empty list if no episodes are found
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
