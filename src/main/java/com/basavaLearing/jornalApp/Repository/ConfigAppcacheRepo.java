package com.basavaLearing.jornalApp.Repository;

import org.springframework.stereotype.Repository;

import com.basavaLearing.jornalApp.Entity.AppCacheEntity;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface ConfigAppcacheRepo extends MongoRepository<AppCacheEntity, ObjectId> {

}
