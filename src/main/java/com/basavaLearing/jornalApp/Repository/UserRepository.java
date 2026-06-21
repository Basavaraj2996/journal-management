package com.basavaLearing.jornalApp.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.basavaLearing.jornalApp.Entity.User;

public interface UserRepository extends MongoRepository<User,ObjectId> {
   
	User findByUserName(String username);
	
	User deleteByUserName(String username);
}
