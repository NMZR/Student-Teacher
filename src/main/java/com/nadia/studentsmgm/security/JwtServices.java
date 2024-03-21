package com.nadia.studentsmgm.security;

import com.nadia.studentsmgm.entity.UserCredentials;
import com.nadia.studentsmgm.exception.ResourceAccessException;
import com.nadia.studentsmgm.service.UserCredentialService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.websocket.Decoder;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;
@Service
public class JwtServices {
    private final String SECRET = "68497899edadbdbf179ab53a53f878de2497784b";
    private final double EXPIRATION = 720000;
    private final UserCredentialService userCredentialService;


    public JwtServices(UserCredentialService userCredentialService) {
        this.userCredentialService = userCredentialService;

    }
    public String generateToken(String email){
        Optional<UserCredentials> userCredentials = userCredentialService.getUserByEmail(email);
        if(userCredentials.isEmpty()) throw new ResourceAccessException("user not found");
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", userCredentials.get().getRole());
        return generateToken(claims, userCredentials.get());
    }
    public String generateToken(Map<String, Object> extraclaims, UserDetails userDetails){
        return Jwts
                .builder()
                .claims(extraclaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration (new Date(String.valueOf(System.currentTimeMillis() + EXPIRATION )))
                .signWith(getSigninKey(), SignatureAlgorithm.HS512)
                .compact();

    }
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .setSigningKey(getSigninKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigninKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);

        return Keys.hmacShaKeyFor(keyBytes);
    }
}
