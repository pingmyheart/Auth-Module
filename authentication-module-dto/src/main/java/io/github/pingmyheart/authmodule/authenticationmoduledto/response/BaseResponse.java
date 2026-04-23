package io.github.pingmyheart.authmodule.authenticationmoduledto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BaseResponse {
    private Status status;
    private String message;

    public enum Status {
        SUCCESS,
        FAILURE
    }
}
