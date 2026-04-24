package io.github.pingmyheart.authmodule.authenticationmodule.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Data
@Component
@ConfigurationProperties(prefix = "io.github.auth-module.authentication-module.jwt")
public class JwtConfigurationProperties {
    private String issuer;
    private String signSecret;
    private Integer expiration;
    private TimeUnit expirationTimeUnit;
    private Integer refreshExpiration;
    private TimeUnit refreshExpirationTimeUnit;
}
