package com.basavaLearing.jornalApp.Pojo;

import java.util.ArrayList;
import java.util.List;
import com.basavaLearing.jornalApp.Entity.JournalEntry;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class UserDTO {
	    @NotBlank(message = "Username cannot be blank")
	    private String userName;

	    @NotBlank(message = "Password cannot be blank")
	    private String password;

	    private List<String> roles = new ArrayList<>();

	    // Optional: If journal entries are needed in DTO
	    private List<JournalEntry> journalEntries = new ArrayList<>();
	    
	    private String email;
	    
	    private boolean sentimentAnalysis;

	    public UserDTO() {}

	    public UserDTO(String userName, String password, List<String> roles, List<JournalEntry> journalEntries, String email, boolean sentimentAnalysis) {
	        this.userName = userName;
	        this.password = password;
	        this.roles = roles;
	        this.journalEntries = journalEntries;
	        this.email=email;
	        this.sentimentAnalysis=sentimentAnalysis;
	    }
	    

}
