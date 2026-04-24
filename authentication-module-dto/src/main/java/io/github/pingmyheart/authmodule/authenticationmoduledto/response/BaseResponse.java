package io.github.pingmyheart.authmodule.authenticationmoduledto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.pingmyheart.authmodule.authenticationmoduledto.enums.ResponseCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse {
    @Builder.Default
    private Status status = ResponseCodeEnum.OK.getStatus();
    @Builder.Default
    private String message = ResponseCodeEnum.OK.getMessage();
    @Builder.Default
    private Integer code = ResponseCodeEnum.OK.getCode();

    public enum Status {
        SUCCESS,
        FAILURE
    }
}
