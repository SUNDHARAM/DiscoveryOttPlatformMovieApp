package com.example.movie.utility;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.example.movie.bean.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTutility {

	private static String secret="My_Secret";
	private static long expiration = 60*60*1000;
	
	
	public String generateJwt(User user) {
		
		long millisec = System.currentTimeMillis()+expiration;
		Date issuedate = new Date(millisec); 
		
		//claims
		Claims claims = Jwts.claims().setIssuer(String.valueOf(user.getId())).setIssuedAt(issuedate).setExpiration(issuedate);
		
		claims.put("name", user.getName());
		
		//generate jwt using claims
		return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secret).compact();
	}
	

	public void verifyToken(String validate) throws Exception {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(validate);
		}catch(Exception e) {
			throw new Exception();
		}
	}
}
