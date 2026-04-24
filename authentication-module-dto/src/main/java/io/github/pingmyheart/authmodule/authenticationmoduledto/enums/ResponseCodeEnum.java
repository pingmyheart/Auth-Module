package io.github.pingmyheart.authmodule.authenticationmoduledto.enums;

import io.github.pingmyheart.authmodule.authenticationmoduledto.response.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.Optional;

@AllArgsConstructor
@Getter
public enum ResponseCodeEnum {
    OK(200, "", BaseResponse.Status.SUCCESS, HttpStatus.OK),
    UNAUTHORIZED(401, "Unauthorized", BaseResponse.Status.FAILURE, HttpStatus.UNAUTHORIZED),
    ACCOUNT_DISABLED(402, "Account is disabled", BaseResponse.Status.FAILURE, HttpStatus.UNAUTHORIZED),
    USERNAME_ALREADY_IN_USE(403, "Username already in use", BaseResponse.Status.FAILURE, HttpStatus.BAD_REQUEST),
    EMAIL_ALREADY_IN_USE(404, "Email already in use", BaseResponse.Status.FAILURE, HttpStatus.BAD_REQUEST),
    LOGIN_SESSION_NOT_FOUND(405, "Login session does not exists", BaseResponse.Status.FAILURE, HttpStatus.NOT_FOUND);

    private final Integer code;
    private final String message;
    private final BaseResponse.Status status;
    private final HttpStatus httpStatus;

    public static ResponseCodeEnum getResponseCodeByCode(Integer errorCode) {
        Optional<ResponseCodeEnum> responseCode = Arrays.stream(ResponseCodeEnum.values())
                .filter(responseCodesEnum -> errorCode.equals(responseCodesEnum.getCode()))
                .findFirst();
        return responseCode.orElse(null);
    }
}
