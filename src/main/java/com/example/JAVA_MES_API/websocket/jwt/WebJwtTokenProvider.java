package com.example.JAVA_MES_API.websocket.jwt;

import java.security.Key;
import java.security.PublicKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class WebJwtTokenProvider {

    private final Key key;

    public WebJwtTokenProvider(@Value("${jwt.secret}") String secretKey) {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public boolean validateCheck(String token) {
        Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
        return true;
    }

    public String getUserId(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
