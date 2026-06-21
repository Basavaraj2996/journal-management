package com.basavaLearing.jornalApp.Entity;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document (collection="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
	@Id
	private ObjectId id;
	@Indexed (unique =true)
	@NotBlank(message = "Username cannot be blank")
	private String userName;
	@NotBlank(message = "password cannot be blank")
	private String password;
	private String email;
	private boolean sentimentAnalysis;
	@DBRef
	private List<JournalEntry> journalEntries = new ArrayList<>();
	private List<String> roles =new ArrayList<>();

}
