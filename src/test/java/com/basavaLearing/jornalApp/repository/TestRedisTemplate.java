package com.basavaLearing.jornalApp.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class TestRedisTemplate {
	
	@Autowired
	RedisTemplate<String, String> redisTemplate;
	
	@Disabled
	@Test
	void redisTemplateTest() {
		 
		redisTemplate.opsForValue().set("email", "Rocky@gmail.com");
		
		Object email=redisTemplate.opsForValue().get("email");
		System.out.println("email value :"+ email);
		assertNotNull(email);
		
	}

}
