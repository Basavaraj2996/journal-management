package com.basavaLearing.jornalApp.Utility;

import com.basavaLearing.jornalApp.Entity.User;
import com.basavaLearing.jornalApp.Pojo.UserDTO;

public class UserMapper {
	 // Private constructor to prevent instantiation
    private UserMapper() {
        throw new UnsupportedOperationException("Utility class");
    }
	public static User toEntity(UserDTO dto) {
        return User.builder()
                .userName(dto.getUserName())
                .password(dto.getPassword())
                .roles(dto.getRoles())
                .journalEntries(dto.getJournalEntries())
                .email(dto.getEmail())
                .sentimentAnalysis(dto.isSentimentAnalysis())
                .build();
    }

    public static UserDTO toDTO(User entity) {
        return new UserDTO(
                entity.getUserName(),
                entity.getPassword(),
                entity.getRoles(),
                null,
                entity.getEmail(),
                entity.isSentimentAnalysis() 
                 // if you're ignoring journalEntries for now
        );
    }

}
