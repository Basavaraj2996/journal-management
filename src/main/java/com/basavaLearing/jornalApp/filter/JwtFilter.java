package com.basavaLearing.jornalApp.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.basavaLearing.jornalApp.Service.UserDetailsServiceImpl;
import com.basavaLearing.jornalApp.utilis.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class JwtFilter  extends OncePerRequestFilter {
    // reading bearer token from the header 
	// extacting username from the claims ( payload )
	//fwtching all user details from the Db by passing username
	//validate teh username basically checks expiry date of token
	//adding authentication object into security contenxt holder to use it in controller 
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String header = request.getHeader("Authorization");
        String token = null;
        String username=null;
        if (header != null && header.startsWith("Bearer ")) {
            token = header.substring(7);
            username=jwtUtil.extractUsername(token);
        }	
        if(username !=null) {
        	UserDetails userDetails =userDetailsService.loadUserByUsername(username);
        	if(jwtUtil.validateToken(token,userDetails.getUsername())) {
        		UsernamePasswordAuthenticationToken auth= new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
        		auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        		SecurityContextHolder.getContext().setAuthentication(auth);
        	}
        }
        filterChain.doFilter(request, response);
	}
 
}
