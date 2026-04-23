package io.github.pingmyheart.authmodule.authenticationmoduledto.authenticationmodule.controller;

import io.github.pingmyheart.authmodule.authenticationmoduledto.request.RegisterMFARequestBean;
import io.github.pingmyheart.authmodule.authenticationmoduledto.request.VerifyLoginRequestBean;
import io.github.pingmyheart.authmodule.authenticationmoduledto.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/mfa")
public class MFAController {
    @PostMapping
    public ResponseEntity<? extends BaseResponse> generateMFA(@RequestHeader("Authorization") String authHeader) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @PutMapping
    public ResponseEntity<? extends BaseResponse> registerMFA(@RequestHeader("Authorization") String authHeader,
                                                              @RequestBody RegisterMFARequestBean requestBean) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @PostMapping("/login")
    public ResponseEntity<? extends BaseResponse> verifyLogin(@RequestHeader("Authorization") String authHeader,
                                                              @RequestBody VerifyLoginRequestBean requestBean) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
