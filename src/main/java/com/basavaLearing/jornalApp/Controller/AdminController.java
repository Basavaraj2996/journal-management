package com.basavaLearing.jornalApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.basavaLearing.jornalApp.Service.UserService;

import com.basavaLearing.jornalApp.Entity.User;
import com.basavaLearing.jornalApp.Repository.UserRepository;

@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	UserRepository userRepository ;
	@Autowired
	UserService userService;
	
	@GetMapping("/all-users")
	public ResponseEntity<?> getAllUsers(){
		List<User> allUsers=userRepository.findAll();
		if(!allUsers.isEmpty()) {
			return new ResponseEntity<>(allUsers,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/create-admin-user")
	public ResponseEntity<?> createAdmin( @RequestBody User user) {
		userService.saveAdminUser(user);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	

}
