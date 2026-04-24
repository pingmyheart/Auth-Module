package io.github.pingmyheart.authmodule.authenticationmodule.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "io.github.auth-module.authentication-module.init")
public class InitConfigurationProperties {
    private AdminUser adminUser;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AdminUser {
        private String username;
        private String password;
        private String email;
    }
}
