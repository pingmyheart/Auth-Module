package io.github.pingmyheart.authmodule.authenticationmoduledto.response;

import io.github.pingmyheart.authmodule.authenticationmoduledto.enums.AdditionalActionEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class AdditionalActionRequiredResponseBean extends BaseResponse {
    private AdditionalActionEnum additionalAction;
}
