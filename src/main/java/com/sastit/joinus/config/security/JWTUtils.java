package com.sastit.joinus.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

public class JWTUtils {
    private static String secret;

    static {
        secret = System.getenv("JWT_SECRET");
        if (secret == null) {
            secret = "secret";
        }
    }

    public static String createToken(String username) {
        long expiration = 2 * 60 * 60 * 1000L; // 2 hours
        return JWT.create()
            .withClaim("username", username)
            .withExpiresAt(new Date(System.currentTimeMillis() + expiration))
            .sign(Algorithm.HMAC256(secret));
    }

    public static String getUsernameFromToken(String token) {
        token = token.replace("Bearer ", "");
        return JWT.decode(token).getClaim("username").asString();
    }

    public static boolean verify(String token, String username) {
        try {
            JWT.require(Algorithm.HMAC256(secret))
                .withClaim("username", username).build()
                .verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
}
