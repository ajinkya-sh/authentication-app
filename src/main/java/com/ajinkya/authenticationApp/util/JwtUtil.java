package com.ajinkya.authenticationApp.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    private static final String SECRET_KEY = "mysecretkey";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 Hour


    public static String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        byte[] secretKeyBytes = SECRET_KEY.getBytes();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encode(secretKeyBytes))
                .compact();
    }


    public static Claims extractClaims(String token) {
        byte[] secretKeyBytes = SECRET_KEY.getBytes();
        return Jwts.parser()
                .setSigningKey(Base64.getEncoder().encode(secretKeyBytes))
                .parseClaimsJws(token)
                .getBody();
    }

    public static void validateToken(String token, String username) {
        String extractedUsername = extractClaims(token).getSubject();
        if (username.equals(extractedUsername)) {
            isTokenExpired(token);
        }
    }

    private static boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    public static String getEmail(String token) {
        return extractClaims(token).getSubject();
    }
}
