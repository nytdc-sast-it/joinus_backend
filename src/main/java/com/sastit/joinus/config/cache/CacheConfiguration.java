package com.sastit.joinus.config.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Ticker;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Configuration
@Component
public class CacheConfiguration {
    @Bean
    public CacheManager cacheManager(Ticker ticker) {
        SimpleCacheManager manager = new SimpleCacheManager();
        manager.setCaches(
            Arrays.asList(
                buildCache("configuration", ticker, 1, TimeUnit.HOURS),     // 配置缓存 1 小时
                buildCache("user", ticker, 3, TimeUnit.SECONDS),            // 用户缓存 3 秒
                buildCache("candidate", ticker, 3, TimeUnit.SECONDS),       // 申请缓存 3 秒
                buildCache("club", ticker, 1, TimeUnit.MINUTES),            // 社团缓存 1 分钟
                buildCache("api", ticker, 1, TimeUnit.MINUTES),             // 申请接口状态 缓存 1 分钟
                buildCache("department", ticker, 1, TimeUnit.MINUTES)       // 部门缓存 1 分钟
            )
        );
        return manager;
    }

    private CaffeineCache buildCache(String name, Ticker ticker, int expireTime, TimeUnit timeUnit) {
        return new CaffeineCache(name, Caffeine.newBuilder()
            .expireAfterWrite(expireTime, timeUnit)
            .maximumSize(100)
            .ticker(ticker)
            .build());
    }

    @Bean
    public Ticker ticker() {
        return Ticker.systemTicker();
    }
}
