package com.LeaderboardService.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class LeaderboardService {

    private final WebClient webClient;

    public LeaderboardService(WebClient.Builder webClientBuilder) {
        // Set the base URL for your external API
        this.webClient = webClientBuilder.baseUrl("").build();
    }

    @Cacheable(value = "leaderboardCache", key = "#root.method.name")
    public Mono<String> getLeaderboardData() {
        // Make the API call to fetch leaderboard data
        return webClient.get()
                        .uri("")  // Use an empty string because the base URL already includes the full path
                        .retrieve()
                        .bodyToMono(String.class);
    }
}
