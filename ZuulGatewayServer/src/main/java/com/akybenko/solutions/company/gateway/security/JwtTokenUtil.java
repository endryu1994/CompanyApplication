package com.akybenko.solutions.company.gateway.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import static com.akybenko.solutions.company.gateway.constant.Constants.JWT.JWT_TOKEN_EXPIRATION_PROPERTY_NULL;
import static com.akybenko.solutions.company.gateway.constant.Constants.JWT.JWT_TOKEN_SECRET_PROPERTY_NULL;

@Component
public class JwtTokenUtil {

    private final Environment env;

    public JwtTokenUtil(Environment env) {
        this.env = env;
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    Boolean validateToken(String token, UserDetails userDetails) {
        String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    private Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> resolver) {
        Claims claims = getAllClaimsFromToken(token);
        return resolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        String secret = Objects.requireNonNull(env.getProperty("jwt.secret"), JWT_TOKEN_SECRET_PROPERTY_NULL);
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        String secret =
                Objects.requireNonNull(env.getProperty("jwt.secret"), JWT_TOKEN_SECRET_PROPERTY_NULL);
        String validityDate =
                Objects.requireNonNull(env.getProperty("jwt.token.validity"), JWT_TOKEN_EXPIRATION_PROPERTY_NULL);
        long current = System.currentTimeMillis();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(current))
                .setExpiration(new Date(current + Long.parseLong(validityDate)))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}
