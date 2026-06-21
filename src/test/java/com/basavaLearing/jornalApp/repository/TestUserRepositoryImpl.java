package com.basavaLearing.jornalApp.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.basavaLearing.jornalApp.Entity.User;
import com.basavaLearing.jornalApp.Repository.UserRepositoryImpl;

@SpringBootTest
class TestUserRepositoryImpl {
	@Autowired
	UserRepositoryImpl userRepositoryImpl;
	
	@Test
	void getUserForSATest() {
		List<User> users=userRepositoryImpl.getUserForSA();
		assertEquals(2, users.size(), "Expected 2 users in the list");
	}

}
