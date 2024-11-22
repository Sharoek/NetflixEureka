package com.leaderboard.controller;


import com.leaderboard.service.LeaderboardClientService;  // Correct import
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class LeaderboardController {

    private final LeaderboardClientService leaderboardClientService;

    @Autowired
    public LeaderboardController(LeaderboardClientService leaderboardClientService) {
        this.leaderboardClientService = leaderboardClientService;
    }

    @GetMapping("/leaderboard")
    public Mono<String> getLeaderboard() {
        return leaderboardClientService.getLeaderboard();
    }
}
