package io.github.pingmyheart.authmodule.authenticationmodule.model.redis.entity;

import io.github.pingmyheart.authmodule.authenticationmoduledto.enums.AdditionalActionEnum;
import io.github.pingmyheart.authmodule.authenticationmoduledto.enums.MFATypeEnum;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class UserLoginCacheEntity {
    private String userId;
    private AdditionalActionEnum action;
    private MFATypeEnum channel;
    private String generatedOTP;
}
