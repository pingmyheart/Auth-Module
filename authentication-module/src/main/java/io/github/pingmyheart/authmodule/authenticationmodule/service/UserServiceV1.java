package io.github.pingmyheart.authmodule.authenticationmodule.service;

import io.github.pingmyheart.authmodule.authenticationmodule.model.mariadb.entity.RoleEntity;
import io.github.pingmyheart.authmodule.authenticationmodule.model.mariadb.entity.UserEntity;
import io.github.pingmyheart.authmodule.authenticationmodule.model.mariadb.entity.UserRoleAssociationEntity;
import io.github.pingmyheart.authmodule.authenticationmodule.model.mariadb.repository.RoleRepository;
import io.github.pingmyheart.authmodule.authenticationmodule.model.mariadb.repository.UserRepository;
import io.github.pingmyheart.authmodule.authenticationmodule.model.mariadb.repository.UserRoleAssociationRepository;
import io.github.pingmyheart.authmodule.authenticationmoduledto.enums.ResponseCodeEnum;
import io.github.pingmyheart.authmodule.authenticationmoduledto.enums.UserRoleEnum;
import io.github.pingmyheart.authmodule.authenticationmoduledto.request.CreateUserRequestBean;
import io.github.pingmyheart.authmodule.authenticationmoduledto.response.BaseResponse;
import io.github.pingmyheart.authmodule.authenticationmoduledto.response.CreateUserResponseBean;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceV1 {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRoleAssociationRepository userRoleAssociationRepository;

    public BaseResponse createUser(CreateUserRequestBean requestBean) {
        // Check if user already exists by username or email
        if (Boolean.TRUE.equals(userRepository.existsByEmail(requestBean.getEmail()))) {
            return BaseResponse.builder()
                    .status(ResponseCodeEnum.USERNAME_ALREADY_IN_USE.getStatus())
                    .message(ResponseCodeEnum.USERNAME_ALREADY_IN_USE.getMessage())
                    .code(ResponseCodeEnum.USERNAME_ALREADY_IN_USE.getCode())
                    .build();
        }
        if (Boolean.TRUE.equals(userRepository.existsByUsername(requestBean.getUsername()))) {
            return BaseResponse.builder()
                    .status(ResponseCodeEnum.USERNAME_ALREADY_IN_USE.getStatus())
                    .message(ResponseCodeEnum.USERNAME_ALREADY_IN_USE.getMessage())
                    .code(ResponseCodeEnum.USERNAME_ALREADY_IN_USE.getCode())
                    .build();
        }

        //Create user
        UserEntity newUser = userRepository.save(UserEntity.builder()
                .username(requestBean.getUsername())
                .email(requestBean.getEmail())
                .password(passwordEncoder.encode(requestBean.getPassword()))
                .enabled(Boolean.TRUE)
                .build());
        List<String> userRolesIds = requestBean.getRoles()
                .stream()
                .map(UserRoleEnum::getRole)
                .map(roleRepository::findByRole)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(RoleEntity::getId)
                .toList();
        userRolesIds.forEach(id -> userRoleAssociationRepository.save(UserRoleAssociationEntity.builder()
                .userId(newUser.getId())
                .roleId(id)
                .build()));
        return CreateUserResponseBean.builder()
                .username(newUser.getUsername())
                .roles(requestBean.getRoles())
                .build();
    }
}
