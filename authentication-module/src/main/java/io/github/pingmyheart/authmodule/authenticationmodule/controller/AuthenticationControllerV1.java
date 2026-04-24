package io.github.pingmyheart.authmodule.authenticationmodule.controller;

import io.github.pingmyheart.authmodule.authenticationmodule.service.AuthenticationServiceV1;
import io.github.pingmyheart.authmodule.authenticationmoduledto.enums.ResponseCodeEnum;
import io.github.pingmyheart.authmodule.authenticationmoduledto.request.LogoutRequestBean;
import io.github.pingmyheart.authmodule.authenticationmoduledto.request.RefreshRequestBean;
import io.github.pingmyheart.authmodule.authenticationmoduledto.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationControllerV1 {
    private final AuthenticationServiceV1 authenticationService;

    @PostMapping("/token")
    public ResponseEntity<? extends BaseResponse> login(@RequestHeader("Authorization") String authHeader) {
        String base64Credentials = authHeader.substring("Basic ".length());
        byte[] decodedBytes = Base64.getDecoder().decode(base64Credentials);
        String decoded = new String(decodedBytes);

        String[] credentials = decoded.split(":", 2);
        BaseResponse serviceResponse = authenticationService.login(credentials[0], credentials[1]);
        return new ResponseEntity<>(serviceResponse, ResponseCodeEnum.getResponseCodeByCode(serviceResponse.getCode()).getHttpStatus());
    }

    @PutMapping("/token")
    public ResponseEntity<? extends BaseResponse> refresh(@RequestHeader("Authorization") String authHeader,
                                                          @RequestBody RefreshRequestBean requestBean) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @DeleteMapping("/token")
    public ResponseEntity<? extends BaseResponse> logout(@RequestHeader("Authorization") String authHeader,
                                                         @RequestBody LogoutRequestBean requestBean) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
