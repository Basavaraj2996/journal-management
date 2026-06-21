package com.basavaLearing.jornalApp.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.basavaLearing.jornalApp.Entity.User;
import com.basavaLearing.jornalApp.Pojo.UserDTO;
import com.basavaLearing.jornalApp.Service.UserDetailsServiceImpl;
import com.basavaLearing.jornalApp.Service.UserService;
import com.basavaLearing.jornalApp.Utility.UserMapper;
import com.basavaLearing.jornalApp.utilis.JwtUtil;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController 
@RequestMapping("/public")
@Slf4j
public class PublicController {
	//@Autowired  as for the sonnarlint implemented the construtor injector instead of field injector.
	private UserService userService;
	private AuthenticationManager authenticationManager;
	private UserDetailsServiceImpl userDetailsService;
	private JwtUtil jwtutil;
	
	public PublicController(UserService userService, AuthenticationManager authenticationManager,UserDetailsServiceImpl userDetailsService,JwtUtil jwtutil) {
		this.userService=userService;
		this.authenticationManager=authenticationManager;
		this.userDetailsService =userDetailsService;
		this.jwtutil =jwtutil;
	}
	
	@GetMapping()
	public ResponseEntity<String> healthcheck(){
		return ResponseEntity.ok("ok");
	}
	
	@PostMapping("/signup")
	public ResponseEntity<String> signUp(@Valid @RequestBody UserDTO user) {
		log.debug("Username received : {}",user.getUserName());
		User userEntity=UserMapper.toEntity(user);
	    userService.userSaveEntry(userEntity);
	    return ResponseEntity.ok("User saved successfully");
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@Valid @RequestBody UserDTO user) {
		try {
			//Manually invoking authentication manager as username and passwords are passing in request
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
			UserDetails userDetails=userDetailsService.loadUserByUsername(user.getUserName());
			String token =jwtutil.generateToken(userDetails.getUsername());
			return new ResponseEntity<>(token,HttpStatus.OK);
		}catch(Exception e) {
			log.error("Excpetion occured while createAuthenticationToken",e);
			return new  ResponseEntity<>("Incorrect Username and Possward",HttpStatus.BAD_REQUEST);
		}
	}
	

}
