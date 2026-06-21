package com.basavaLearing.jornalApp.Config;

import com.basavaLearing.jornalApp.Service.UserDetailsServiceImpl;
import com.basavaLearing.jornalApp.filter.JwtFilter;

import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@Slf4j
public class SpringSecurity {

    //@Autowired
    private UserDetailsServiceImpl userDetailsService;
    private JwtFilter jwtFilter;
    
    public SpringSecurity( UserDetailsServiceImpl userDetailsService, JwtFilter jwtfilter) {
    	this.userDetailsService=userDetailsService;
    	this.jwtFilter=jwtfilter;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	
    	log.info("🛡️ Initializing SecurityFilterChain...");
    	http.authorizeHttpRequests(request -> request
                        .requestMatchers("/public/**","/actuator/**").permitAll()
                        .requestMatchers("/journal/**", "/user/**").hasRole("USER")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                //.httpBasic(Customizer.withDefaults()) removed as JWT token authentication implemented
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); // adding jwt filter before basic authentication , first step to implement jwt  
    	log.info("✅ SecurityFilterChain initialized successfully");
        return http.build();
    }

	
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
    	log.info("🔧 Creating AuthenticationManager...");
    	 AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
    	    builder.userDetailsService(userDetailsService)
    	           .passwordEncoder(passwordEncoder());
    	    AuthenticationManager authenticationManager = builder.build();
    	    log.info("✅ AuthenticationManager created successfully");
    	    return authenticationManager;
    }
    //as part of JWT 
	/*
	 * @Bean
	 * 
	 * @Override AuthenticationManager authenticationmanagerBean() throws Exception
	 * { return super.authenticationmanagerBean(); }
	 */
    
	 
}

