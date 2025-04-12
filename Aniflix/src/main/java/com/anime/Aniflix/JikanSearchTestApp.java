//package com.anime.Aniflix;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.web.client.RestTemplate;
//
//import java.net.URLEncoder;
//import java.nio.charset.StandardCharsets;
//import java.util.List;
//import java.util.Map;
//
//@SpringBootApplication
//public class JikanSearchTestApp implements CommandLineRunner {
//
//    public static void main(String[] args) {
//        SpringApplication.run(JikanSearchTestApp.class, args);
//    }
//
//    @Override
//    public void run(String... args) {
//        String query = "One Piece";
//        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
//        String searchUrl = "https://api.jikan.moe/v4/anime?q=" + encodedQuery;
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        try {
//            // 1. Search anime by name
//            Map<String, Object> searchResponse = restTemplate.getForObject(searchUrl, Map.class);
//            List<Map<String, Object>> dataList = (List<Map<String, Object>>) searchResponse.get("data");
//
//            Map<String, Object> match = null;
//
//            for (Map<String, Object> anime : dataList) {
//                String title = (String) anime.get("title");
//                String titleEnglish = (String) anime.get("title_english");
//                if ((title != null && title.equalsIgnoreCase(query)) ||
//                        (titleEnglish != null && titleEnglish.equalsIgnoreCase(query))) {
//                    match = anime;
//                    break;
//                }
//            }
//
//            if (match != null) {
//                Integer animeId = (Integer) match.get("mal_id");
//                String episodesUrl = "https://api.jikan.moe/v4/anime/" + animeId + "/episodes";
//
//                int page = 1;  // Start from page 1
//                boolean hasMoreEpisodes = true;
//
//                System.out.println("📺 Episodes for: " + match.get("title_english"));
//
//                // 2. Fetch all episodes by paginating
//                while (hasMoreEpisodes) {
//                    // Fetch current page of episodes
//                    Map<String, Object> episodeResponse = restTemplate.getForObject(episodesUrl + "?page=" + page, Map.class);
//                    List<Map<String, Object>> episodes = (List<Map<String, Object>>) episodeResponse.get("data");
//
//                    // Print episodes on the current page
//                    for (Map<String, Object> episode : episodes) {
//                        System.out.println("🔹 Ep " + episode.get("mal_id") + ": " + episode.get("title"));
//                        System.out.println("    ➤ Aired: " + episode.get("aired"));
//                        System.out.println("    📝 Synopsis: " + episode.get("synopsis"));
//                        System.out.println();
//                    }
//
//                    // Check if there are more pages
//                    hasMoreEpisodes = episodeResponse.containsKey("pagination") &&
//                            ((Map<String, Object>) episodeResponse.get("pagination")).get("has_next_page") != null &&
//                            (Boolean) ((Map<String, Object>) episodeResponse.get("pagination")).get("has_next_page");
//
//                    // Move to the next page
//                    page++;
//                }
//
//            } else {
//                System.out.println("❌ Exact match not found for: " + query);
//            }
//
//        } catch (Exception e) {
//            System.out.println("❌ Error: " + e.getMessage());
//        }
//    }
//}
