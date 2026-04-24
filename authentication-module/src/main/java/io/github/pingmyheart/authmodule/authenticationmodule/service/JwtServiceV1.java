package io.github.pingmyheart.authmodule.authenticationmodule.service;

import io.github.pingmyheart.authmodule.authenticationmodule.model.mariadb.entity.RoleEntity;
import io.github.pingmyheart.authmodule.authenticationmodule.model.mariadb.entity.UserEntity;
import io.github.pingmyheart.authmodule.authenticationmodule.model.mariadb.repository.RoleRepository;
import io.github.pingmyheart.authmodule.authenticationmodule.properties.JwtConfigurationProperties;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtServiceV1 {
    private final RoleRepository roleRepository;
    private final JwtConfigurationProperties jwtConfigurationProperties;

    public String generateAccessToken(UserEntity user) {
        List<RoleEntity> userRoles = roleRepository.findByUserId(user.getId());
        Instant expiration = Instant.now().plusMillis(jwtConfigurationProperties.getExpirationTimeUnit().toMillis(jwtConfigurationProperties.getExpiration()));

        byte[] bytes = Decoders.BASE64.decode(jwtConfigurationProperties.getSignSecret());
        SecretKey key = Keys.hmacShaKeyFor(bytes);

        return Jwts.builder()
                .subject(user.getId())
                .expiration(Date.from(expiration))
                .issuedAt(Date.from(Instant.now()))
                .issuer(jwtConfigurationProperties.getIssuer())
                .claim("type", "Bearer")
                .claim("roles", userRoles.stream().map(RoleEntity::getRole).toList())
                .signWith(key)
                .compact();
    }

    public String generateRefreshToken(UserEntity user) {
        return null;
    }

    public Boolean isTokenValid(String token) {
        try {
            byte[] bytes = Decoders.BASE64.decode(jwtConfigurationProperties.getSignSecret());
            SecretKey key = Keys.hmacShaKeyFor(bytes);
            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);
            return Boolean.TRUE;
        } catch (MalformedJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
            log.error("Invalid JWT token: {}", ex.getMessage());
            return Boolean.FALSE;
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token: {}", ex.getMessage());
            return Boolean.FALSE;
        }
    }
}
