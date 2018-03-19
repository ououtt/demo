package com.example.demo.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author guzemin@songxiaocai.com
 * @create 2018-03-18 13:43
 **/
@Configuration
//session超时时间,单位秒
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 60 * 60 * 24)
@EnableCaching
public class RedisCacheConfig extends CachingConfigurerSupport {

    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);

        //默认超时时间,单位秒
        //根据缓存名称设置超时时间,0为不超时
        cacheManager.setDefaultExpiration(300);
        cacheManager.setLoadRemoteCachesOnStartup(true);

        Map<String, Long> expires = new ConcurrentHashMap<>(128);
        cacheManager.setExpires(expires);

        return cacheManager;
    }
}
