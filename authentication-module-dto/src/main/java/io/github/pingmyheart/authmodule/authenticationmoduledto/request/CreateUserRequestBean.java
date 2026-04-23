package io.github.pingmyheart.authmodule.authenticationmoduledto.request;

import io.github.pingmyheart.authmodule.authenticationmoduledto.enums.UserRoleEnum;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
public class CreateUserRequestBean {
    private String username;
    private String email;
    private String password;
    private List<UserRoleEnum> roles;
}
