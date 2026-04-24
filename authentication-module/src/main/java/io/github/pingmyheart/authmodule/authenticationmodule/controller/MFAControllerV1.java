package io.github.pingmyheart.authmodule.authenticationmodule.controller;

import io.github.pingmyheart.authmodule.authenticationmodule.service.MFAServiceV1;
import io.github.pingmyheart.authmodule.authenticationmoduledto.enums.ResponseCodeEnum;
import io.github.pingmyheart.authmodule.authenticationmoduledto.request.InitiateMFARequestBean;
import io.github.pingmyheart.authmodule.authenticationmoduledto.request.RegisterMFARequestBean;
import io.github.pingmyheart.authmodule.authenticationmoduledto.request.VerifyLoginRequestBean;
import io.github.pingmyheart.authmodule.authenticationmoduledto.response.BaseResponse;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/mfa")
@Slf4j
public class MFAControllerV1 {
    private final MFAServiceV1 mfaService;

    @PostMapping("/initiate")
    public ResponseEntity<? extends BaseResponse> initiateMFA(@RequestHeader("Authorization") String authHeader,
                                                              @RequestBody InitiateMFARequestBean requestBean) {
        String userId = Jwts.parser().build().parseSignedClaims(authHeader.substring("Bearer ".length())).getPayload().getSubject();
        BaseResponse serviceResponse = mfaService.initiate(userId, requestBean);
        return new ResponseEntity<>(serviceResponse, ResponseCodeEnum.getResponseCodeByCode(serviceResponse.getCode()).getHttpStatus());
    }

    @PostMapping("/verify")
    public ResponseEntity<? extends BaseResponse> verifyMFA(@RequestHeader("Authorization") String authHeader,
                                                            @RequestBody VerifyLoginRequestBean requestBean) {
        String userId = Jwts.parser().build().parseSignedClaims(authHeader.substring("Bearer ".length())).getPayload().getSubject();
        BaseResponse serviceResponse = mfaService.verify(userId, requestBean);
        return new ResponseEntity<>(serviceResponse, ResponseCodeEnum.getResponseCodeByCode(serviceResponse.getCode()).getHttpStatus());
    }

    @PutMapping
    public ResponseEntity<? extends BaseResponse> registerMFA(@RequestHeader("Authorization") String authHeader,
                                                              @RequestBody RegisterMFARequestBean requestBean) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
