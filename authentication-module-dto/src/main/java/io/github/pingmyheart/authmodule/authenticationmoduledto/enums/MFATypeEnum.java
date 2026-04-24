package io.github.pingmyheart.authmodule.authenticationmoduledto.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum MFATypeEnum {
    APP,
    MAIL
}
