package com.basavaLearing.jornalApp.Service;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
//import org.springframework.security.core.userdetails.User;
import com.basavaLearing.jornalApp.Entity.User;
public class UserArgumentsProvider implements ArgumentsProvider{

	@Override
	public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
		
		System.out.print("running the user Save unit testing .......");
		Stream<? extends Arguments> saved =Stream.of(
				Arguments.of(User.builder().userName("shashi").password("shashi").build()),
                Arguments.of(User.builder().userName("indu").password("indu").build())
				);
		System.out.print("Steam of  the user are created  .......");
		
		return saved;
	}

}
