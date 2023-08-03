package com.study.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

/**
 * Jwt 인증을 처리하는 Provider
 */
@Component
public class JwtAuthenticationProvider {

    private final String SECRET_KEY;
    private final Long ACCESS_TOKEN_EXPIRATION;
    private final Long REFRESH_TOKEN_EXPIRATION;
    private final CustomUserDetailsService userDetailsService;

    public JwtAuthenticationProvider(
            @Value("${jwt.secret-key}") String SECRET_KEY,
            @Value("${jwt.access-token-expiration}") Long ACCESS_TOKEN_EXPIRATION,
            @Value("${jwt.refresh-token-expiration}") Long REFRESH_TOKEN_EXPIRATION,
            CustomUserDetailsService userDetailsService) {
        this.SECRET_KEY = Base64.getEncoder().encodeToString(SECRET_KEY.getBytes());
        this.ACCESS_TOKEN_EXPIRATION = ACCESS_TOKEN_EXPIRATION;
        this.REFRESH_TOKEN_EXPIRATION = REFRESH_TOKEN_EXPIRATION;
        this.userDetailsService = userDetailsService;
    }


    /**
     * JWT를 생성합니다.
     *
     * @param userId 사용자 아이디
     * @param tokenExpiration 토큰 유효시간
     * @return JWT
     */
    public String createJwt(String userId, Long tokenExpiration) {
        return Jwts.builder()
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .setSubject(userId)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    /**
     * 토큰을 분석합니다.
     *
     * @param jwt
     * @return
     * @throws JwtException 유효기간만료, 유효하지 않은 토큰 파싱예외
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
     * JWT를 분석하여 아이디를 조회한 후
     * 아이디가 DB에 존재시 UserDetailsService를 통해 UesrDetails 생성하여 인증토큰 생성
     * @param jwt
     * @return 인증토큰
     */

    public Authentication getAuthentication(String jwt) {
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

    /**
     * 리프레쉬토큰 유효기간을 반환합니다.
     *
     * @return 엑세스토큰 유효기간
     */
    public Long REFRESH_TOKEN_EXPIRATION() {
        return REFRESH_TOKEN_EXPIRATION;
    }
}
