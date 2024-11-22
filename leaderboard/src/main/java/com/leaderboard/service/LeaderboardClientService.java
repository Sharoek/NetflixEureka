package com.leaderboard.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class LeaderboardClientService {


    private DiscoveryClient discoveryClient;
    private final WebClient webClient;

    @Autowired
    public LeaderboardClientService(WebClient.Builder webClientBuilder, DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
        
        // Get the first instance of the LEADERBOARDSERVICE
        ServiceInstance instance = discoveryClient.getInstances("LEADERBOARDSERVICE").get(0);
        
        // Configure WebClient to use the instance's URI
        this.webClient = webClientBuilder.baseUrl(instance.getUri().toString()).build();
    }

    public Mono<String> getLeaderboard() {
        return webClient.get()
                         .uri("/leaderboard")  // Path for the leaderboard API on 8081
                         .retrieve()
                         .bodyToMono(String.class)  // Convert the response to a Mono<String>
                         .doOnTerminate(() -> System.out.println("Request completed"))  // Log when the request completes
                         .doOnError(error -> System.out.println("Error occurred: " + error.getMessage()));  // Log any errors
    }
}