package io.github.pingmyheart.authmodule.authenticationmoduledto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class LogoutResponseBean extends BaseResponse {
}
