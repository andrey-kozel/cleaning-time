package dev.akozel.cleaningtime.rest.security.jwt.service;

import dev.akozel.cleaningtime.core.common.time.TimeService;
import dev.akozel.cleaningtime.rest.feature.auth.model.ApplicationUserDetails;
import dev.akozel.cleaningtime.rest.security.jwt.exception.InvalidJwtTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * JwtTokenServiceImpl. This service generates and parses JWT token
 * <p>
 * Date: 22/02/2020
 *
 * @author Andrey Kozel
 */
@Component
public class JwtTokenServiceImpl implements JwtTokenService {

    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String AUTHORITY_CLAIM = "authority";
    private static final String ID_CLAIM = "id";

    @Value("${jwt.validity}")
    private Long validityTimeMillis;

    @Value("${jwt.secret}")
    private String secret;

    private TimeService timeService;

    @Autowired
    public JwtTokenServiceImpl(TimeService timeService) {
        this.timeService = timeService;
    }

    @Override
    public String getUsernameByAccessToken(String jwtToken) {
        try {
            Claims claims = extractClaims(jwtToken);
            validateForExpiration(claims);
            return claims.getSubject();
        } catch (Exception ex) {
            throw new InvalidJwtTokenException(ex.getMessage(), ex);
        }
    }

    private Claims extractClaims(String token) {
        String trimmedToken = token.replace(TOKEN_PREFIX, "");
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(trimmedToken)
                .getBody();
    }

    private void validateForExpiration(Claims claims) {
        if (claims.getExpiration().before(timeService.getCurrentTime())) {
            throw new RuntimeException("Token is expired");
        }
    }

    @Override
    public String generateAccessToken(ApplicationUserDetails user) {
        String token = Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(timeService.getCurrentTime())
                .setExpiration(timeService.getCurrentTmeFromNow(validityTimeMillis))
                .claim(AUTHORITY_CLAIM, user.getAuthorities())
                .claim(ID_CLAIM, user.getId())
                .signWith(SignatureAlgorithm.HS384, secret)
                .compact();
        return TOKEN_PREFIX + token;
    }

}
