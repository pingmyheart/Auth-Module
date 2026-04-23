package io.github.pingmyheart.authmodule.authenticationmoduledto.request;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class RegisterMFARequestBean {
    private String totp;
}
