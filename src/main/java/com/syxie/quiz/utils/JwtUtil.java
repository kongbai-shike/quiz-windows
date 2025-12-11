package com.syxie.quiz.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;

public class JwtUtil {

    // 建议从配置加载，并保证长度≥256bit
    private static final Key SECRET_KEY = Keys.hmacShaKeyFor(
            "mySuperSecretKey1234567890abcdef-mySuperSecretKey1234567890abcdef"
                    .getBytes(StandardCharsets.UTF_8));

    // 过期时间：1小时
    private static final long EXPIRATION_MILLIS = 60 * 60 * 1000L;

    // 兼容旧方法：仅用户名
    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MILLIS))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    // 新方法：携带必要 claims（例如 role、uid）
    public static String generateToken(String username, Map<String, Object> claims) {
        return Jwts.builder()
                .setSubject(username)
                .addClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MILLIS))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public static Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public static String parseToken(String token) {
        return parseClaims(token).getSubject();
    }

    public static int parseRole(String token) {
        Object roleObj = parseClaims(token).get("role");
        if (roleObj == null) return -1;
        if (roleObj instanceof Number) return ((Number) roleObj).intValue();
        try { return Integer.parseInt(roleObj.toString()); } catch (Exception e) { return -1; }
    }

    public static boolean isExpired(String token) {
        try {
            Date exp = parseClaims(token).getExpiration();
            return exp != null && exp.before(new Date());
        } catch (JwtException e) {
            return true;
        }
    }
}