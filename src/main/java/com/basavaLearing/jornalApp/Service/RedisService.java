package com.basavaLearing.jornalApp.Service;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RedisService {
	
	
    @Autowired 
    private RedisTemplate<String, String> redisTemplate;
    
    public <T> T get(String key ,Class<T> entityClass) {
    	
    	try {
    		Object o=redisTemplate.opsForValue().get(key);
        	ObjectMapper mapper = new ObjectMapper();
        	if(o !=null) {
        		return mapper.readValue(o.toString(), entityClass);
        	}else {
        		log.debug("data not found in redis hence retrieving from the mangoDB");
        		return null;
        	}
			
		} catch (Exception e) {
			log.error("error in redis get method : {}",e);
			return null;
		}
    	
    	
    }

	public void set(String key, Object body, long ttl) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			String jsonValue=mapper.writeValueAsString(body);
			redisTemplate.opsForValue().set(key, jsonValue, ttl, TimeUnit.SECONDS);
		}catch (Exception e) {
			log.error("error in redis set method : {}",e);
		}
	}
    
    

}
