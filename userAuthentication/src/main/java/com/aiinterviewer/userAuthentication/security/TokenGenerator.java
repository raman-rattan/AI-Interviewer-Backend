package com.aiinterviewer.userAuthentication.security;

import com.aiinterviewer.userAuthentication.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenGenerator implements ITokenGenerator{
    @Override
    public Map<String, String> generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();

        claims.put("email", user.getEmail());

        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "MySecretKeyForUserAuthenticationOfAIInterviewer")
                .compact();

        Map<String, String> map = new HashMap<>();

        map.put("token", token);
        map.put("message", "Login Succesfully");

        return map;
    }
}
