package io.github.pingmyheart.authmodule.authenticationmoduledto.request;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class VerifyLoginRequestBean {
    private String totp;
}
