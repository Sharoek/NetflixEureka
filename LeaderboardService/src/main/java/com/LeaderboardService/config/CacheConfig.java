package com.LeaderboardService.config;

import com.github.benmanes.caffeine.cache.Caffeine;

import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfig {

    @Bean
    public Caffeine caffeineConfig() {
        return Caffeine.newBuilder()
            .expireAfterWrite(30, TimeUnit.MINUTES)  // Cache entries expire after 30 minutes
            .maximumSize(100)  // Maximum of 100 entries in the cache
            .recordStats();  // Enables statistics
    }

    @Bean
    public CacheManager cacheManager(Caffeine caffeine) {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCaffeine(caffeine);
        caffeineCacheManager.setAsyncCacheMode(true);
        return caffeineCacheManager;
    }
}
