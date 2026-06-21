package com.basavaLearing.jornalApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.basavaLearing.jornalApp.Entity.User;
import com.basavaLearing.jornalApp.Pojo.UserDTO;
import com.basavaLearing.jornalApp.Pojo.WhetherReponseDTO;
import com.basavaLearing.jornalApp.Service.UserService;
import com.basavaLearing.jornalApp.Service.WhetherService;



@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private WhetherService weatherService;

	/*
	 * @GetMapping() public List<User> getAllUsers(){ return
	 * userService.getAllentry(); } //will make it for admin 
	 */
	
	@PutMapping()
	public ResponseEntity<String> updateUser( @RequestBody UserDTO user ){ //SecurityContextHolder is like a container where Spring Security keeps your current user's login details. means will be passing username and pasword in header.
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();   // internally spring security will store the user details when he/she successfully login with authentication  
		String userName = authentication.getName();  
		User userinDb =userService.findByUserName(userName);
		if(userinDb !=null) {
			userService.userSaveEntry(userinDb);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping()
	public ResponseEntity<String> DeleteUser( @RequestBody UserDTO user ){ 
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();     
		String userName = authentication.getName();  
		userService.deleteByUserName(userName);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping()
	public ResponseEntity<String> wetherPostCast(@RequestBody UserDTO user ){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		WhetherReponseDTO response=weatherService.getCurrentWhether("Karnataka");
		String greating =" ";
		if(response !=null) {
			greating+="weather fells like :"+response.getCurrent().getFeelslike();
		}
		return new ResponseEntity<>("HI "+userName +greating, HttpStatus.OK);
	}
	
    
}
