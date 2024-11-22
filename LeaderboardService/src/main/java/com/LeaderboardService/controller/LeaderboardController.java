package com.LeaderboardService.controller;

import com.LeaderboardService.service.LeaderboardService;  // Correct import
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class LeaderboardController {

    private final LeaderboardService leaderboardService;

    @Autowired
    CacheManager cacheManager;
    
    public LeaderboardController(LeaderboardService leaderboardService) {
        this.leaderboardService = leaderboardService;
    }

    @GetMapping("/leaderboard")
    public Mono<String> getLeaderboard() {
        // Fetch and return the leaderboard data
        return leaderboardService.getLeaderboardData();
    }
}
