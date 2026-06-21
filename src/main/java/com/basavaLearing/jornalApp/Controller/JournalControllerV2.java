package com.basavaLearing.jornalApp.Controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
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
import com.basavaLearing.jornalApp.Entity.JournalEntry;
import com.basavaLearing.jornalApp.Entity.User;
import com.basavaLearing.jornalApp.Service.JournalService;
import com.basavaLearing.jornalApp.Service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/journal")
public class JournalControllerV2 {
	
	//private Map<Long , JournalEntry> journalEntries = new HashMap<>();
	@Autowired
	private JournalService service;
	@Autowired
	private UserService userService;
	@GetMapping
    public ResponseEntity<List<JournalEntry>> getAllJournalEntriesOfUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();   // internally spring security will store the user details when he/she successfully login with authentication  
		String userName = authentication.getName(); 
		User user=userService.findByUserName(userName);
		List<JournalEntry> all=user.getJournalEntries();
		if(all !=null && !all.isEmpty()) {
			return new ResponseEntity<>(all,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("id/{myid}")
    public ResponseEntity<JournalEntry> getjournalDetailsById(@PathVariable ObjectId myid) {
		//getEntryById method returns optional<Journalentry>, entry may come or may not come , that is why if not values in getEntrybyId then returns null
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();   // internally spring security will store the user details when he/she successfully login with authentication  
		String userName = authentication.getName(); 
		User user=userService.findByUserName(userName);
		List<JournalEntry>collect=user.getJournalEntries().stream().filter(x -> x.getId().equals(myid)).collect(Collectors.toList());
		if(!collect.isEmpty()) {
			Optional<JournalEntry > entry= service.getEntryById(myid);
			if(entry.isPresent() ) {
				return new ResponseEntity<>(entry.get(),HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		//return service.getEntryById(myid);
	}
	
	@PostMapping
	public ResponseEntity<?>  createEntry (@Valid @RequestBody JournalEntry myentry ) {
		try{
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
			System.out.println("Authenticated user: " + authentication.getName() + ", Roles: " + authentication.getAuthorities());// internally spring security will store the user details when he/she successfully login with authentication  
			String userName = authentication.getName();
			System.out.println("Received for user: " + userName + ", title: " + myentry.getTitle());
			service.journalSaveEntry(myentry,userName);
			return new ResponseEntity<>(myentry, HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@DeleteMapping("id/{myid}")
    public ResponseEntity<?> deletejournalDetailsById(@PathVariable ObjectId myid) {
		System.out.println("delete request for the id : " +myid);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();   // internally spring security will store the user details when he/she successfully login with authentication  
		String userName = authentication.getName(); 
		System.out.println("delete request for the id for the User : " +userName);
		service.deleteJournalEntryById(myid,userName);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	  @PutMapping("id/{myid}") //update 
	  public ResponseEntity<?> updatejournalDetailsById(@PathVariable ObjectId myid, @RequestBody JournalEntry newEntry) { 
		  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();   // internally spring security will store the user details when he/she successfully login with authentication  
		  String userName = authentication.getName(); 
		  User user=userService.findByUserName(userName);
			List<JournalEntry>collect=user.getJournalEntries().stream().filter(x -> x.getId().equals(myid)).collect(Collectors.toList());
		if(!collect.isEmpty()) {
	      JournalEntry oldEntry =service.getEntryById(myid).orElse(null); 
	      if(oldEntry !=null) {
			  oldEntry.setContent(newEntry.getContent() !=null &&!newEntry.getContent().equalsIgnoreCase("") ? newEntry.getContent() :oldEntry.getContent());
			  oldEntry.setTitle(newEntry.getTitle() !=null &&!newEntry.getTitle().equalsIgnoreCase("") ? newEntry.getTitle() :oldEntry.getTitle()); 
			  service.journalSaveEntry(oldEntry); 
			  return new ResponseEntity<>(oldEntry, HttpStatus.OK); 
		  } 
		}
		  return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
	 }
	 
    
}
