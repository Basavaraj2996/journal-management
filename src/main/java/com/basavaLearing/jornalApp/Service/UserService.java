package com.basavaLearing.jornalApp.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.basavaLearing.jornalApp.Entity.JournalEntry;
import com.basavaLearing.jornalApp.Entity.User;
import com.basavaLearing.jornalApp.Pojo.WhetherReponseDTO;
import com.basavaLearing.jornalApp.Repository.JournalRepository;
import com.basavaLearing.jornalApp.Repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserService {
	
	
	private UserRepository userRepository;
	//constuctor based injection
	public UserService(UserRepository userRepository) {
		this.userRepository=userRepository;
	}
	private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
    public boolean userSaveEntry(User user) {
    	try {
    	if (user.getUserName() == null) {
            throw new RuntimeException("Username is null");
        }
        log.info("request comes to service saveEntry: " + user.getUserName());
        if(findByUserName( user.getUserName())==null) {
        	user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
        }
        userRepository.save(user);
        return true ;
    	}
        catch (Exception e) {
        	return false;
        }
    	
    }
    public void userSaveNewEntry(User user) {  //not used currently 
    	if (user.getUserName() == null) {
            throw new RuntimeException("Username is null");
        }
    	log.info("request comes to service saveEntry: " + user.getUserName());
    	userRepository.save(user);
    }
	public List<User> getAllentry() {
		return userRepository.findAll();
	}
	public Optional<User> getEntryById(ObjectId myid) {
		return userRepository.findById(myid);
	}
	public void deleteUserEntryById(ObjectId myid) {
		userRepository.deleteById(myid);
	}
	public User findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}
	public User deleteByUserName(String userName) {
		return userRepository.deleteByUserName(userName);
	}
	public void saveAdminUser(User adminUser) {
		if (adminUser.getUserName() == null) {
            throw new RuntimeException("Username is null");
        }
		log.info("request comes to service saveNewAdmin: " + adminUser.getUserName());
        if(findByUserName( adminUser.getUserName())==null) {
        	adminUser.setPassword(passwordEncoder.encode(adminUser.getPassword()));
        	adminUser.setRoles(Arrays.asList("USER","ADMIN"));
        }
        userRepository.save(adminUser);
		
	}
	
}
