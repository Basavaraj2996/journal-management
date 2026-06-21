package com.basavaLearing.jornalApp.Entity;


import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;


@Document (collection="config_journal_app")
@Data
@NoArgsConstructor
public class AppCacheEntity {
	
	 private String key;
	 private String value;
	
}
