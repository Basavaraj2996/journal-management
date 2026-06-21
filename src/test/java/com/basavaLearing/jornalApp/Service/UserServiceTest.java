package com.basavaLearing.jornalApp.Service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.ArgumentsSources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.basavaLearing.jornalApp.Entity.User;
import com.basavaLearing.jornalApp.Repository.UserRepository;

@SpringBootTest
public class UserServiceTest {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;
	@Disabled
	@Test 
	public void TestfindByUserName() {
		User user =userRepository.findByUserName("raj ");
		assertTrue(!user.getJournalEntries().isEmpty());
		
	}
	@ParameterizedTest
	@ArgumentsSource(UserArgumentsProvider.class)
	public void TestSaveUser(User user) {
		System.out.print("inside the TestSaveUser method .......");
		assertTrue(userService.userSaveEntry(user));
												
	}
	
	
	

}
