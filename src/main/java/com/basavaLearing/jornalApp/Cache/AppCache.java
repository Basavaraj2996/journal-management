package com.basavaLearing.jornalApp.Cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.basavaLearing.jornalApp.Entity.AppCacheEntity;
import com.basavaLearing.jornalApp.Repository.ConfigAppcacheRepo;

import jakarta.annotation.PostConstruct;

@Component
public class AppCache {
	
	@Autowired
	ConfigAppcacheRepo configRepository;
     
	public Map<String,String > AppDbProperty=new HashMap<>();
	@PostConstruct
	public void init() {
		List<AppCacheEntity> all=configRepository.findAll();
			for(AppCacheEntity property: all) {
				AppDbProperty.put(property.getKey(), property.getValue());
			}
		
	}
}
