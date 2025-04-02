package com.LeaderboardService.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class LeaderboardService {

private final Map<String, Integer> leaderboard = new ConcurrentHashMap<>();

    /**
     * Adds a player's score to the leaderboard.
     *
     * @param player The player's name.
     * @param score  The player's score.
     */
    public void addScore(String player, int score) {
        leaderboard.merge(player, score, Integer::sum);
    }

        /**
     * Retrieves the top N players sorted by their scores.
     *
     * @param topN The number of players to retrieve.
     * @return A sorted list of the top N players and their scores.
     */
    @Cacheable(value = "leaderboardCache", key = "#topN")
    public List<Map.Entry<String, Integer>> getTopPlayers(int topN) {
        return leaderboard.entrySet()
                .stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue())) // Sort by descending scores
                .limit(topN)
                .toList();
    }

    /**
     * Clears the leaderboard data.
     */
    public void clearLeaderboard() {
        leaderboard.clear();
    }

    // private final WebClient webClient;

    // public LeaderboardService(WebClient.Builder webClientBuilder) {
    //     // Set the base URL for your external API
    //     this.webClient = webClientBuilder.baseUrl("").build();
    // }

    // @Cacheable(value = "leaderboardCache", key = "#root.method.name")
    // public Mono<String> getLeaderboardData() {
    //     // Make the API call to fetch leaderboard data
    //     return webClient.get()
    //                     .uri("")  // Use an empty string because the base URL already includes the full path
    //                     .retrieve()
    //                     .bodyToMono(String.class);
    // }
}
