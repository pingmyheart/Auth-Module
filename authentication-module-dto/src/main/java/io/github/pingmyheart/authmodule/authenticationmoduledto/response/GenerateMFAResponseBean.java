package io.github.pingmyheart.authmodule.authenticationmoduledto.response;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class GenerateMFAResponseBean {
    private String totpString;
}
