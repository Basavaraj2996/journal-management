package com.basavaLearing.jornalApp.Service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {
	
	@Autowired
	EmailService emailService;
	@Test
	void testSendmail() {
		emailService.sendSimpleEmail("basavaraj99gh@gmail.com", "TestSendmail via spring boot", "Hi Basavaraja , you are doing very well , keep it up and donot distract by your biggest enemy");
	}

}
