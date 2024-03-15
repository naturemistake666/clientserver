package com.example.clientservice.service.impl;

import com.example.clientservice.service.JwtTokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Класс JwtRefreshTokenServiceImpl предоставляет реализацию интерфейса JwtTokenService
 * для управления Refresh JWT-токенами доступа.
 *
 * Этот сервис позволяет генерировать, проверять и извлекать утверждения из Refresh JWT-токенов доступа.
 *
 * @author v.kindyalov
 */
public class JwtRefreshTokenServiceImpl implements JwtTokenService {

    private final SecretKey jwtRefreshSecret;

    /**
     * Конструирует объект JwtAccessTokenServiceImpl с предоставленным секретным ключом доступа JWT.
     *
     * @param jwtRefreshSecret Секретный ключ доступа JWT.
     */
    public JwtRefreshTokenServiceImpl(@Value("${jwt.secret.refresh}") String jwtRefreshSecret) {
        this.jwtRefreshSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtRefreshSecret));
    }

    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token, jwtRefreshSecret);
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
                .setExpiration(new Date(System.currentTimeMillis() + 1800 * 1000))
                .signWith(jwtRefreshSecret, SignatureAlgorithm.HS256)
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
