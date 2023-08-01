package com.study.config.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

/**
 * Jwt 인증 Provider
 */
@Component
public class JwtAuthenticationProvider {

    private final String SECRET_KEY;
    private final String ACCESS_TOKEN = "accessToken";
    private final String REFRESH_TOKEN = "refreshToken";
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

    public String createJwt(String userId, Long tokenExpiration) {
        return Jwts.builder()
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .setSubject(userId)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public Claims parseJwt(String token) throws JwtException {
        String parseToken = token.replace("Bearer ", "");
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(parseToken).getBody();
    }

    public String getJwtFromHeader(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    public Authentication getAuthentication(String jwt) {
        Claims claims = parseJwt(jwt);
        String userId = claims.getSubject();

        CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(userId);

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    /**
     * 토큰이 null이거나 유효기간이 만료한 경우를 검증합니다.
     *
     * @param jwt
     * @return 유효한 토큰인 경우 true
     */
    public boolean validateToken(String jwt) {
        if (jwt == null) {
            return false;
        }

        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwt);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public Long ACCESS_TOKEN_EXPIRATION() {
        return ACCESS_TOKEN_EXPIRATION;
    }

    public Long REFRESH_TOKEN_EXPIRATION() {
        return REFRESH_TOKEN_EXPIRATION;
    }
}
