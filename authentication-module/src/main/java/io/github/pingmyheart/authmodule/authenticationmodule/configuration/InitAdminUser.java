package io.github.pingmyheart.authmodule.authenticationmodule.configuration;

import io.github.pingmyheart.authmodule.authenticationmodule.properties.InitConfigurationProperties;
import io.github.pingmyheart.authmodule.authenticationmodule.service.UserServiceV1;
import io.github.pingmyheart.authmodule.authenticationmoduledto.enums.UserRoleEnum;
import io.github.pingmyheart.authmodule.authenticationmoduledto.request.CreateUserRequestBean;
import io.github.pingmyheart.authmodule.authenticationmoduledto.response.BaseResponse;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class InitAdminUser {
    private final InitConfigurationProperties initConfigurationProperties;
    private final UserServiceV1 userService;

    @PostConstruct
    void initNewAdminUser() {
        InitConfigurationProperties.AdminUser adminUser = initConfigurationProperties.getAdminUser();
        CreateUserRequestBean serviceRequest = CreateUserRequestBean.builder()
                .username(adminUser.getUsername())
                .email(adminUser.getEmail())
                .password(adminUser.getPassword())
                .roles(List.of(UserRoleEnum.ROLE_ADMIN))
                .build();
        BaseResponse response = userService.createUser(serviceRequest);
        log.info(response.getMessage());
    }
}
