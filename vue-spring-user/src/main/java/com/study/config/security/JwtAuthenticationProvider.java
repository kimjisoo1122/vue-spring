package com.study.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

/**
 * Jwt 인증을 처리하는 프로바이더
 */
@Component
@Slf4j
public class JwtAuthenticationProvider {

    private final String SECRET_KEY;
    private final Long ACCESS_TOKEN_EXPIRATION;
    private final CustomUserDetailsService userDetailsService;

    public JwtAuthenticationProvider(
            @Value("${jwt.secret-key}") String SECRET_KEY,
            @Value("${jwt.access-token-expiration}") Long ACCESS_TOKEN_EXPIRATION,
            CustomUserDetailsService userDetailsService) {
        this.SECRET_KEY = Base64.getEncoder().encodeToString(SECRET_KEY.getBytes());
        this.ACCESS_TOKEN_EXPIRATION = ACCESS_TOKEN_EXPIRATION;
        this.userDetailsService = userDetailsService;
    }


    /**
     * Jwt를 생성합니다.
     *
     * @param userId 사용자 아이디
     * @param tokenExpiration 토큰 유효시간
     * @return Jwt
     */
    public String createJwt(String userId, Long tokenExpiration) {
        String jwt = Jwts.builder()
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .setSubject(userId)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();

        log.info("JWT 생성 완료. 사용자 아이디: {}", userId);

        return jwt;
    }

    /**
     * 토큰을 분석합니다.
     *
     * @param jwt jwt
     * @return 분석한 jwt Claims 정보
     * @throws JwtException 유효기간 만료, 유효하지 않은 토큰 파싱예외
     */
    public Claims parseJwt(String jwt) throws JwtException {
        String parseToken = jwt.replace("Bearer ", "");
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(parseToken).getBody();
    }

    /**
     * 리퀘스터헤더로부터 jwt를 가져옵니다.
     *
     * @param request 리퀘스트 객체
     * @return jwt
     */
    public String getJwtFromHeader(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    /**
     * Jwt에서 아이디를 파싱하여 인증처리 후 인증토큰을 생성하여 반환합니다.
     *
     * @param jwt
     * @return 인증토큰
     */
    public Authentication createAuthentication(String jwt) {
        Claims claims = parseJwt(jwt);
        String userId = claims.getSubject();

        CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(userId);

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    /**
     * 엑세스토큰 유효기간을 반환합니다.
     *
     * @return 엑세스토큰 유효기간
     */
    public Long ACCESS_TOKEN_EXPIRATION() {
        return ACCESS_TOKEN_EXPIRATION;
    }
}
