package com.ceinsys.config;

import io.jsonwebtoken.*;
import com.ceinsys.model.Role;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.function.Function;

@Component
public class JWTUtil {


    private static final String secret_key = "@#%^^#&qkfhqiufhq9164716871eqiqhdbkajb";

    @Value("${jwt.expiration}")
    private Long expiration;


    public String getToken (String email, Role role){
        Instant now  = Instant.now();
        Instant expirationTime = now.plusMillis(expiration);
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+expiration))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();

    }

    private Key getSignKey() {
        byte[] keyBite = Decoders.BASE64.decode(secret_key);
        return Keys.hmacShaKeyFor(keyBite);
    }
    public <T> T getClamsFromToken(String token, Function<Claims, T> clamsResolver){
        final Claims claims = getAllClamsFromToken(token);
        return clamsResolver.apply(claims);
    }
    private Claims getAllClamsFromToken(String token){
        return Jwts.parser().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
    }
    public boolean validateToken(String token, String email){
        final String tokenEmail = getEmailFromToken(token);
        return (tokenEmail.equals(email) && !isTokenExpired(token));
    }
    public String getEmailFromToken(String token){
        return getClamsFromToken(token,Claims::getSubject);

    }
    public Date getExpirationDateFromToken(String token){
        return getClamsFromToken(token, Claims::getExpiration);
    }

    public boolean isTokenExpired (String token){
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
}
