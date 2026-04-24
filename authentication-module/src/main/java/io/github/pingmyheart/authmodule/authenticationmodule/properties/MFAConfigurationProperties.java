package io.github.pingmyheart.authmodule.authenticationmodule.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "io.github.auth-module.authentication-module.mfa")
public class MFAConfigurationProperties {
    private String issuer;
}
