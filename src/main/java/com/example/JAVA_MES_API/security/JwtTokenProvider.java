
package com.example.JAVA_MES_API.security;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

public class JwtTokenProvider {
	
	private static String SECRET_KEY;

    @Value("${jwt.secret}")
    public void setSecretKey(String secretKey) {
        JwtTokenProvider.SECRET_KEY = secretKey;
    }

    public static String getSecretKey() {
        return SECRET_KEY;
    }

    public static String generateToken(String userId, long expireDays) {
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

        Date now = new Date();
        Date expiration = new Date(now.getTime() + (expireDays * 24L * 60L * 60L * 1000L));

        return Jwts.builder()
                .setSubject(userId)             
                .setIssuedAt(now)               
                .setExpiration(expiration)     
                .signWith(key, SignatureAlgorithm.HS256) 
                .compact();
    }
}