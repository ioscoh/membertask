package com.example.membertask.global.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {
    // 비밀키의 경우 일반적으로 환경변수로 관리합니다.
    // 편의를 위해 속성으로 선언해 놓고 사용하는 예시입니다.
    private String secret = "h2c8nySL8kTSy9T2IK3z7aM7clExaob70qA2s4sZ2fo=";
    /**
     * 토큰 만들기
     */

    public String createJwt(String memberEmail) {

        // 1. 서명 만들기
        SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes());

        // 2. 데이터 준비
        String subject = memberEmail; // 사용자 준비
        Date now = new Date();                // 현재시간
        Date expiration = new Date(now.getTime() + 1000 * 60); // 만료시간 설정 1분뒤

        // 2. 토큰 만들기
        String jwt = Jwts.builder()
                .subject(subject)
                .issuedAt(now)
                .claim("role", "admin") // 💡 커스텀 하게 활용하는 방법
                .claim("key1", "value1")
                .claim("key2", "value2")
                .claim("key3", "value3")
                .expiration(expiration)
                .signWith(secretKey)
                .compact();
        return jwt;
    }
    /**
     * 토큰을 검증하고 memberId 를 반환합니다.
     */
        public String verifyToken(String token) {
            // 1. 서명 만들기
            SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes());

            // 2. 검증
            Claims claims = Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            // 2. 사용자 추출
            String subject = claims.getSubject();
            // String value1  = (String) claims.get("key1"); // 커스텀하게 설정한 요소 추출


            // 반환
            return subject;
    }
}
