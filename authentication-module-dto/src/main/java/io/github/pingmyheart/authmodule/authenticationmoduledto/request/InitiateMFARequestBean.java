package io.github.pingmyheart.authmodule.authenticationmoduledto.request;

import io.github.pingmyheart.authmodule.authenticationmoduledto.enums.MFATypeEnum;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class InitiateMFARequestBean {
    private MFATypeEnum mfaType;
}
