package io.github.pingmyheart.authmodule.authenticationmoduledto.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum UserRoleEnum {
    ROLE_ADMIN("admin"),
    ROLE_USER("user");

    private final String role;
}
