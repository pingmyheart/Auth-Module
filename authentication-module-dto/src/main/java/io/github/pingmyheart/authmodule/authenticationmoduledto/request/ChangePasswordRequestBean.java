package io.github.pingmyheart.authmodule.authenticationmoduledto.request;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ChangePasswordRequestBean {
    private String actualPassword;
    private String newPassword;
    private String confirmNewPassword;
}
