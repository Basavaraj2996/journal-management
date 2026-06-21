package com.basavaLearing.jornalApp.utilis;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	// 1 ,2 ,3 methods are used in generating token from login public method, there
	// i have manually implemented the basic userpassord authentication and
	public String generateToken(String userName) { // 1
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, userName);
	}

	private String createToken(Map<String, Object> claims, String userName) {// 2

		return Jwts.builder().addClaims(claims).setSubject(userName).setHeaderParam("typ", "JWT")
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 10)) // 5 minutes
				.signWith(getSigningKey()).compact();
	}

	private static final String SECRET_KEY = "TAK+sdhausGHD#78gh*Z*$Vsdasdwe#sdjsldkf";

	private Key getSigningKey() { // 3
		return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
	}

	public boolean validateToken(String token, String string) { // 6

		return (!isTokenExpired(token));

	}

	private boolean isTokenExpired(String token) { // 7
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) { // 8
		return getClaimFromToken(token).getExpiration();
	}

	public Claims getClaimFromToken(String token) {// Function<Claims, T> resolver //5
		return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
		// return resolver.apply(claims);
		// return claims;
	}

	public String extractUsername(String token) { // 4
		return getClaimFromToken(token).getSubject();
	}

}
