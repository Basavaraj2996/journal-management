package com.basavaLearing.jornalApp.Scheduler;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.basavaLearing.jornalApp.Cache.AppCache;
import com.basavaLearing.jornalApp.Entity.User;
import com.basavaLearing.jornalApp.Repository.UserRepositoryImpl;
import com.basavaLearing.jornalApp.Service.EmailService;
import com.basavaLearing.jornalApp.enums.Sentiments;

@Component
public class UserScheduler {
	@Autowired
	private UserRepositoryImpl userRepositoryImpl;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private AppCache appCache;
	
	/*
	 * this will help to schedule a cron job with time base, here it will trigger
	 * this method every week Sunday 9 am
	 */
	
	//@Scheduled(cron="0 0 9 * * SUN")  
	public void findUserandSendSAmail() {
		
		/*
		 * this is the service , fetch the user journals entries and list out the all
		 * the journal sentiment and then find out the highest sentiment and same will be send to email 
		 * finally send the email to user
		 */
		
		List<User> users= userRepositoryImpl.getUserForSA();
		for(User user : users) {
			List<Sentiments> entries =user.getJournalEntries().stream()
					.filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS)))
					.map(x -> x.getSentiment())
					.collect(Collectors.toList());
			Map<Sentiments, Integer > sentementCount = new HashMap<>();
			for (Sentiments sentiment: entries) {
				if(sentiment !=null) {
				sentementCount.put(sentiment, sentementCount.getOrDefault(sentiment, 0)+1);
				}
			}
			Sentiments mostFrequentSentiment=null;
			int maxCount=0;
			for(Map.Entry<Sentiments, Integer > entry : sentementCount.entrySet() ) {
				if(entry.getValue()> maxCount ) {
					maxCount=entry.getValue();
					mostFrequentSentiment=entry.getKey();
				}
			}
			if(mostFrequentSentiment !=null) {
			    emailService.sendSimpleEmail(user.getEmail(), "Sentiment for the last 7 Days", mostFrequentSentiment.toString());
			}
		}
	}
	
	/*
	 * below method is will trigger the appcache init method for every 10 minutes to
	 * load the data
	 */ 
	@Scheduled(cron="0 0/10 * * * ?")
	public void appCacheScheduler() {
		
		appCache.init();
		
	}

}
