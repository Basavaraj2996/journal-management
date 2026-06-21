package com.basavaLearing.jornalApp.Service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import com.basavaLearing.jornalApp.Entity.User;
//import org.springframework.security.core.userdetails.User;

import com.basavaLearing.jornalApp.Repository.UserRepository;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserServiceTestMockito {
	@InjectMocks
	private UserDetailsServiceImpl userDetailsService;
	@Mock
	private UserRepository userRepository;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void loadUserByUsernameTest() {
		when(userRepository.findByUserName("ram")).thenReturn(User.builder().userName("ram").password("shddflashenhaoshd").roles(new ArrayList<>()).build());
		UserDetails user=userDetailsService.loadUserByUsername("ram");
		assertNotNull(user);
	}

}
