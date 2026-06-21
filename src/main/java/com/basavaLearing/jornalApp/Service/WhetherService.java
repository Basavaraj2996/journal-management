package com.basavaLearing.jornalApp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.basavaLearing.jornalApp.Cache.AppCache;
import com.basavaLearing.jornalApp.Pojo.WhetherReponseDTO;

import lombok.extern.slf4j.Slf4j;



@Component
@Slf4j
public class WhetherService {
	
	public enum keys{
		WEATHER_API;
	}
	
	@Value("${weather.api.key}")
	public String apiKey ;
	@Autowired
	public AppCache api;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private RedisService redisService;

	public WhetherReponseDTO getCurrentWhether(String city) {
		WhetherReponseDTO redisresponse=redisService.get("weather_of_"+city, WhetherReponseDTO.class);
		if(redisresponse !=null) {
			log.debug("redisresponse is not null hence passwing the redis stored whether details ");
		    return redisresponse;
		}else {
			String finalAPI=api.AppDbProperty.get(keys.WEATHER_API.toString()).replace("<api_key>", apiKey).replace("<city>",city);
			log.debug("final weather api is : {}",finalAPI);
			ResponseEntity<WhetherReponseDTO> response=restTemplate.exchange(finalAPI,HttpMethod.GET, null, WhetherReponseDTO.class);
			//handle the error scenario and throw respective error task 
			log.debug("got the resposne from the weather api {}",response.getBody());
			if(response.getBody() != null) {
				redisService.set("weather_of_"+city,response.getBody(),300L);
				log.debug("storing the whether details to redis for city : {} ",city);
				
			}
			return response.getBody();
		}
	}

}
