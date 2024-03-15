package com.example.clientservice.service.impl;

import com.example.clientservice.service.JwtTokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Класс JwtAccessTokenServiceImpl предоставляет реализацию интерфейса JwtTokenService
 * для управления Access JWT-токенами доступа.
 *
 * Этот сервис позволяет генерировать, проверять и извлекать утверждения из Access JWT-токенов доступа.
 *
 * @author v.kindyalov
 */
@Service
public class JwtAccessTokenServiceImpl implements JwtTokenService {
    private final SecretKey jwtAccessSecret;

    /**
     * Конструирует объект JwtAccessTokenServiceImpl с предоставленным секретным ключом доступа JWT.
     *
     * @param jwtAccessSecret Секретный ключ доступа JWT.
     */
    public JwtAccessTokenServiceImpl(@Value("${jwt.secret.access}") String jwtAccessSecret) {
        this.jwtAccessSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtAccessSecret));
    }

    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }


    @Override
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token, jwtAccessSecret);
        return claimsResolver.apply(claims);
    }


    @Override
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    @Override
    public String generateToken(Map<String,Object> extraClaims, UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 10000 * 60))
                .signWith(jwtAccessSecret, SignatureAlgorithm.HS256)
                .compact();
    }


    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }


    @Override
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    @Override
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


    @Override
    public Claims extractAllClaims(String token, Key secretKey) {
        return Jwts
                .parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
