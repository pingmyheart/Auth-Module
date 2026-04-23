package io.github.pingmyheart.authmodule.authenticationmoduledto.response;

import io.github.pingmyheart.authmodule.authenticationmoduledto.enums.UserRoleEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class CreateUserResponseBean extends BaseResponse {
    private String username;
    private List<UserRoleEnum> roles;
}
