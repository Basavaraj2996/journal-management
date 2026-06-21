package com.basavaLearing.jornalApp.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.basavaLearing.jornalApp.Entity.JournalEntry;
import com.basavaLearing.jornalApp.Entity.User;
import com.basavaLearing.jornalApp.Repository.JournalRepository;

@Service
public class JournalService {
	@Autowired
	private JournalRepository repository;
	@Autowired
	private UserService userService;
	@Transactional
    public void journalSaveEntry(JournalEntry entry, String userName) {
    	User user=userService.findByUserName(userName);
    	System.out.println("getttng the user name: "+ user.getUserName());
		entry.setDate(LocalDateTime.now());
    	JournalEntry saved=repository.save(entry);
    	System.out.println("saved JournalEntry : "+ saved);
    	user.getJournalEntries().add(saved);
    	System.out.println("getting the saved JournalEntry from the DB  : "+ userService.findByUserName(userName).getJournalEntries());
    	userService.userSaveEntry(user);
    	
    }
    public void journalSaveEntry(JournalEntry entry) {
    	repository.save(entry);
    }
	public List<JournalEntry> getAllentry() {
		return repository.findAll();
	}
	public Optional<JournalEntry> getEntryById(ObjectId myid) {
		return repository.findById(myid);
	}
	public void deleteJournalEntryById(ObjectId myid, String userName) {
		User user=userService.findByUserName(userName);
		for( JournalEntry entry : user.getJournalEntries()) {
			System.out.println(" UserJournalEntries : "+entry.getId());
		}
		boolean b=user.getJournalEntries().removeIf(x -> x.getId().equals(myid));
		if(b) {
			for( JournalEntry entry : user.getJournalEntries()) {
				System.out.println(" UserJournalEntries  after remvoing the id : "+entry.getId());
			}
			userService.userSaveEntry(user);
			repository.deleteById(myid);
		}
		
	}
}
