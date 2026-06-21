package com.basavaLearing.jornalApp.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.basavaLearing.jornalApp.Entity.User;

public class UserRepositoryImpl {
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	public List<User> getUserForSA() {
		Query query =new Query();
		query.addCriteria(Criteria.where("sentimentAnalysis" ).is(true));
		query.addCriteria(Criteria.where("email" ).regex("^[A-Za-z0-9+_.-]+@(.+)$"));
		List<User> users =mongoTemplate.find(query, User.class);
		return users;
	}

}
