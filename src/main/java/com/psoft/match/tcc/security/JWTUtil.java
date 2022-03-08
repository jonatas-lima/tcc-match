package com.psoft.match.tcc.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JWTUtil {

    private String SECRET = "SECRET";

    private Long EXPIRATION_DATE = 1200000L;

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_DATE))
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
                .compact();
    }

    public boolean isTokenValid(String token) {
        Claims claims = this.getClaims(token);
        boolean isValid = false;

        if (claims != null) {
            String username = claims.getSubject();
            Date expirationDate = claims.getExpiration();
            Date now = new Date(System.currentTimeMillis());

            isValid = username != null && expirationDate != null && now.before(expirationDate);
        }

        return isValid;
    }

    public String extractUsername(String token) {
        Claims claims = this.getClaims(token);
        if (claims != null) {
            return claims.getSubject();
        }
        return null;
    }

    private Claims getClaims(String token) {
//        try {
            return Jwts.parser().setSigningKey(SECRET.getBytes()).parseClaimsJws(token).getBody();
//        } catch (Exception e) {
//            return null;
//        }
    }
}
