package com.graynaud.eu4savedisplayerbo.config;


import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.cache.CacheManager;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.Duration;
import javax.cache.expiry.TouchedExpiryPolicy;

@Configuration
@EnableCaching
public class CacheConfig {

    @Component
    public static class CachingSetup implements JCacheManagerCustomizer {

        @Override
        public void customize(CacheManager cacheManager) {
            cacheManager.createCache("saveCache", new MutableConfiguration<>()
                    .setExpiryPolicyFactory(TouchedExpiryPolicy.factoryOf(Duration.ETERNAL))
                    .setStoreByValue(false)
                    .setStatisticsEnabled(true));
        }
    }
}
