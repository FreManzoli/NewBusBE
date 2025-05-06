package it.unife.sample.backend.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "my-super-secret-key-for-jwt-256-bit-my-super-secret-key"; // 64-byte key
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 7; // 7 giorni

    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());//chiave utilizzata per firmare il token

    public String generateToken(UUID userId, String email) {//metodo per generare il token
        return Jwts.builder()
                .setSubject(email) //email utente come soggetto del token
                .claim("userId", userId.toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))//imposta la data di scadenza
                .signWith(key, SignatureAlgorithm.HS256)//firma il token con l'algoritmo HS256
                .compact();//converte il token in una stringa
    }

    public Claims validateToken(String token) {//valido il token e restituisco i claims, le informazioni contenute nel token
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("Token scaduto", e);
        } catch (JwtException e) {
            throw new RuntimeException("Token non valido", e);
        }
    }

    public String extractEmail(String token) {//estraggo l'email dal token
        return validateToken(token).getSubject();
    }

    public UUID extractUserId(String token) {//estraggo l'ID utente dal token
        return UUID.fromString(validateToken(token).get("userId", String.class));
    }
}
