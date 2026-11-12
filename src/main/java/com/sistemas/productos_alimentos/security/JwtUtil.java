package com.sistemas.productos_alimentos.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    // Cambia esto por una frase secreta más larga y segura en producción
    private final String SECRET_KEY = "EstaEsUnaClaveSecretaMuyLargaYSeguraParaElSistemaDeAlimentos2026";
    private final long EXPIRATION_TIME = 86400000; // 24 horas en milisegundos


    public SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String userId, String role) {
        return Jwts.builder()
                .setSubject(userId)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, getSigningKey())
                .compact();
    }



    public String extractUserId(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey()) // 🌟 Corregido para 0.11.5
                .build()
                .parseClaimsJws(token)          // 🌟 Corregido para 0.11.5
                .getBody()                      // 🌟 Corregido para 0.11.5
                .getSubject();
    }

    public boolean validateToken(String token, String userId) {
        final String extractedUser = extractUserId(token);
        return (extractedUser.equals(userId) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expiration.before(new Date());
    }

    public String extractRole(String token) {
        return io.jsonwebtoken.Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("role", String.class);
    }

}

