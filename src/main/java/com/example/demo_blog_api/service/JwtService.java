package com.example.demo_blog_api.service;

import com.example.demo_blog_api.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService implements JwtGeneratorInterface {
    @Value("${spring.jwt.secret}")
    private String secret;

    @Value("${spring.jwt.message}")
    private String message;

    public Map<String, String> generateToken (User user) {
        Key key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        String jwtToken = Jwts.builder()
                .subject(user.getNickname())
                .issuedAt(new Date())
                .signWith(key)
                .compact();

        Map<String, String> jwtTokenGen = new HashMap<>();
        jwtTokenGen.put("token", jwtToken);
        jwtTokenGen.put("message", message);
        return jwtTokenGen;
    }
}
