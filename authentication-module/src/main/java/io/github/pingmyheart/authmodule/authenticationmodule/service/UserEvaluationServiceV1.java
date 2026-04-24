package io.github.pingmyheart.authmodule.authenticationmodule.service;

import io.github.pingmyheart.authmodule.authenticationmodule.model.mariadb.entity.UserEntity;
import io.github.pingmyheart.authmodule.authenticationmoduledto.enums.ResponseCodeEnum;
import io.github.pingmyheart.authmodule.authenticationmoduledto.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserEvaluationServiceV1 {
    public Optional<BaseResponse> evaluateUser(UserEntity user) {
        if (Boolean.FALSE.equals(user.getEnabled())) {
            return Optional.of(BaseResponse.builder()
                    .status(ResponseCodeEnum.ACCOUNT_DISABLED.getStatus())
                    .message(ResponseCodeEnum.ACCOUNT_DISABLED.getMessage())

                    .build());
        }
        return Optional.empty();
    }
}
