package com.example.template.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;
import java.util.Date;
import java.util.List;

public class JwtUtils {
    private static final String JWT_SECRET_KEY = "";
    private static final byte[] API_KEY_SECRET_BYTES = DatatypeConverter.parseBase64Binary(JWT_SECRET_KEY);
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(API_KEY_SECRET_BYTES);

    private static final long EXPIRATION = 60 * 60L;

    public static String createJwt(String username, String id, List<String> roles) {
        final Date createdTime = new Date();
        final Date expiredTime = new Date(createdTime.getTime() + EXPIRATION);
        String prefix = Jwts.builder()
                .setHeaderParam("type", "JWT")
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .claim("roles", String.join(",", roles))
                .setId(id)
                .setIssuer("uncle-lv")
                .setIssuedAt(createdTime)
                .setSubject(username)
                .setExpiration(expiredTime)
                .compact();

        return "Bearer " + prefix;
    }

    public static String getId(String jwt) {
        Claims claims = getClaims(jwt);
        return claims.getId();
    }

    private static Claims getClaims(String jwt) {
        String token = jwt.substring(6);
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
