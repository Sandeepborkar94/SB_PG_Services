package com.product.config;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import com.product.app.dto.ProductDTO;

@Configuration
public class RedisConfig
{
	@Bean
    RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) 
    {
        RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration
        		.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(10))
                .disableCachingNullValues()
                .serializeValuesWith(RedisSerializationContext
                		.SerializationPair
                		.fromSerializer(
                        new Jackson2JsonRedisSerializer<>(ProductDTO.class)));

        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(cacheConfiguration)
                .build();
    }

}
