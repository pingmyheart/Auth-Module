package io.github.pingmyheart.authmodule.authenticationmoduledto.authenticationmodule.controller;

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

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {

    @PostMapping("/token")
    public ResponseEntity<? extends BaseResponse> login(@RequestHeader("Authorization") String authHeader) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
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
