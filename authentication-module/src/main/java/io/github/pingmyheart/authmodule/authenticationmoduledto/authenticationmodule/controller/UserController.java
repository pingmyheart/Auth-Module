package io.github.pingmyheart.authmodule.authenticationmoduledto.authenticationmodule.controller;

import io.github.pingmyheart.authmodule.authenticationmoduledto.request.ChangePasswordRequestBean;
import io.github.pingmyheart.authmodule.authenticationmoduledto.request.CreateUserRequestBean;
import io.github.pingmyheart.authmodule.authenticationmoduledto.request.ResetPasswordRequestBean;
import io.github.pingmyheart.authmodule.authenticationmoduledto.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user")
public class UserController {
    @GetMapping("/info")
    public ResponseEntity<? extends BaseResponse> getUserInfo(@RequestHeader("Authorization") String authHeader) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @PostMapping
    public ResponseEntity<? extends BaseResponse> createUser(@RequestBody CreateUserRequestBean requestBean) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @PutMapping("/password")
    public ResponseEntity<? extends BaseResponse> changePassword(@RequestHeader("Authorization") String authHeader,
                                                                 @RequestBody ChangePasswordRequestBean requestBean) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @PostMapping("/password/reset")
    public ResponseEntity<? extends BaseResponse> resetPassword(@RequestBody ResetPasswordRequestBean requestBean) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
