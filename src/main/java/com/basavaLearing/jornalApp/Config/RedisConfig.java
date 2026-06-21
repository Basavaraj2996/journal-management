package com.basavaLearing.jornalApp.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        
		/*
		 * // Use JSON serializer GenericJackson2JsonRedisSerializer serializer = new
		 * GenericJackson2JsonRedisSerializer();
		 * template.setDefaultSerializer(serializer);
		 */
        template.setKeySerializer(new StringRedisSerializer());   //storing the key in plain text 
        template.setValueSerializer(new StringRedisSerializer());
        
        return template;
    }
}
