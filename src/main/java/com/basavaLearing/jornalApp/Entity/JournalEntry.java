package com.basavaLearing.jornalApp.Entity;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.basavaLearing.jornalApp.enums.Sentiments;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document (collection="journal_entries")
@Data
@NoArgsConstructor
public class JournalEntry {
	 @Id
	 private ObjectId id ;
	 @NotBlank(message = "Title is required")
	 private String title;
	 private String content;
	 private LocalDateTime date;
	 private Sentiments sentiment;
}
