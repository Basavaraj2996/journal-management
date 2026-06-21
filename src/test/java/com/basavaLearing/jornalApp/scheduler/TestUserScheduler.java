package com.basavaLearing.jornalApp.scheduler;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.basavaLearing.jornalApp.Scheduler.UserScheduler;

@SpringBootTest
class TestUserScheduler {
	
	@Autowired
	private UserScheduler userScheduler;
	
	@Test 
	void findUserandSendSAmailTest() {
		
		userScheduler.findUserandSendSAmail();
		
	}

}
