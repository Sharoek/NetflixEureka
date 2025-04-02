package com.LeaderboardService.controller;

import com.LeaderboardService.service.LeaderboardService;  // Correct import
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/leaderboard")
public class LeaderboardController {

    private final LeaderboardService leaderboardService;

    @Autowired
    CacheManager cacheManager;
    
    public LeaderboardController(LeaderboardService leaderboardService) {
        this.leaderboardService = leaderboardService;
    }

    // @GetMapping("/leaderboard")
    // public Mono<String> getLeaderboard() {
    //     // Fetch and return the leaderboard data
    //     return leaderboardService.getLeaderboardData();
    // }

     @PostMapping("/add")
    public ResponseEntity<String> addScore(@RequestParam String player, @RequestParam int score) {
        leaderboardService.addScore(player, score);
        return ResponseEntity.ok("Score added!");
    }

    @GetMapping("/top")
    public ResponseEntity<List<Map.Entry<String, Integer>>> getTopPlayers(@RequestParam int topN) {
        return ResponseEntity.ok(leaderboardService.getTopPlayers(topN));
    }

    @DeleteMapping("/clear")
    public ResponseEntity<String> clearLeaderboard() {
        leaderboardService.clearLeaderboard();
        return ResponseEntity.ok("Leaderboard cleared!");
    }
}
