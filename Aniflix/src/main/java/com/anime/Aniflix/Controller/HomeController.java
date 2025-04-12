//package com.anime.Aniflix.Controller;
//
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Controller
//public class HomeController {
//
//    private final RestTemplate restTemplate;
//    private static final String JIKAN_API = "https://api.jikan.moe/v4";
//
//    public HomeController(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }
//
//    @GetMapping("/")
//    public String home(Model model) {
//        try {
//            // Get top anime
//            ResponseEntity<com.anime.Aniflix.Model.JikanResponse<AnimeData>> topAnimeResponse = restTemplate.exchange(
//                    JIKAN_API + "/top/anime?filter=airing",
//                    HttpMethod.GET,
//                    null,
//                    new ParameterizedTypeReference<com.anime.Aniflix.Model.JikanResponse<AnimeData>>() {}
//            );
//
//            // Get recent episodes
//            ResponseEntity<com.anime.Aniflix.Model.JikanResponse<EpisodeData>> recentEpisodesResponse = restTemplate.exchange(
//                    JIKAN_API + "/watch/episodes",
//                    HttpMethod.GET,
//                    null,
//                    new ParameterizedTypeReference<com.anime.Aniflix.Model.JikanResponse<EpisodeData>>() {}
//            );
//
//            // Process and add data to the model
//            model.addAttribute("topAnime", processTopAnime(topAnimeResponse.getBody().getData()));
//            model.addAttribute("recentEpisodes", processEpisodes(recentEpisodesResponse.getBody().getData()));
//
//        } catch (Exception e) {
//            model.addAttribute("error", "Could not fetch anime data: " + e.getMessage());
//        }
//
//        return "index";
//    }
//
//
//
//    private List<Anime> processTopAnime(List<AnimeData> animeList) {
//        return animeList.stream()
//                .limit(10)
//                .map(anime -> {
//                    Anime a = new Anime();
//                    a.setMalId(anime.getMal_id());
//                    a.setTitle(anime.getTitle());
//                    a.setImageUrl(anime.getImages().getJpg().getImage_url());
//                    a.setScore(anime.getScore());
//                    return a;
//                })
//                .collect(Collectors.toList());
//    }
//
//    private List<Episode> processEpisodes(List<EpisodeData> episodes) {
//        return episodes.stream()
//                .limit(12)
//                .map(episode -> {
//                    Episode e = new Episode();
//                    e.setTitle(episode.getTitle());
//                    e.setEpisodeNumber(episode.getEpisode());
//                    e.setAnimeTitle(episode.getEntry().getTitle());
//                    e.setAnimeImage(episode.getEntry().getImages().getJpg().getImage_url());
//                    return e;
//                })
//                .collect(Collectors.toList());
//    }
//}