package com.syxie.quiz.utils;

public class JwtTest {
    public static void main(String[] args) {
        // 使用简单版本的 JWT 测试
        String token = JwtUtil.generateToken("toms");
        System.out.println("token:" + token);

        // 解析JWT令牌
        String username = JwtUtil.parseToken(token);
        System.out.println("=== 解析后的 JWT ===");
        System.out.println("用户名: " + username);
    }
}