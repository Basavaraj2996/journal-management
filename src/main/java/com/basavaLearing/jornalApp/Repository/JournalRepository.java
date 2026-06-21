package com.basavaLearing.jornalApp.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.basavaLearing.jornalApp.Entity.JournalEntry;

public interface JournalRepository extends MongoRepository<JournalEntry,ObjectId> {
  
}
