package io.github.pingmyheart.authmodule.authenticationmodule.service;

import io.github.pingmyheart.authmodule.authenticationmodule.model.mariadb.entity.MFAEntity;
import io.github.pingmyheart.authmodule.authenticationmodule.model.mariadb.entity.UserEntity;
import io.github.pingmyheart.authmodule.authenticationmodule.model.mariadb.repository.MFARepository;
import io.github.pingmyheart.authmodule.authenticationmodule.model.mariadb.repository.UserRepository;
import io.github.pingmyheart.authmodule.authenticationmodule.model.redis.entity.UserLoginCacheEntity;
import io.github.pingmyheart.authmodule.authenticationmodule.model.redis.repository.UserLoginCacheRepository;
import io.github.pingmyheart.authmodule.authenticationmoduledto.enums.AdditionalActionEnum;
import io.github.pingmyheart.authmodule.authenticationmoduledto.enums.ResponseCodeEnum;
import io.github.pingmyheart.authmodule.authenticationmoduledto.response.AdditionalActionRequiredResponseBean;
import io.github.pingmyheart.authmodule.authenticationmoduledto.response.BaseResponse;
import io.github.pingmyheart.authmodule.authenticationmoduledto.response.LoginResponseBean;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceV1 {
    private final UserEvaluationServiceV1 userEvaluationService;
    private final JwtServiceV1 jwtService;
    private final UserRepository userRepository;
    private final MFARepository mfaRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserLoginCacheRepository userLoginCacheRepository;

    public BaseResponse login(String username, String password) {
        //User do not exist
        Optional<UserEntity> user = userRepository.findByEmailOrUsername(username, username);
        if (user.isEmpty()) {
            return BaseResponse.builder()
                    .status(ResponseCodeEnum.UNAUTHORIZED.getStatus())
                    .message(ResponseCodeEnum.UNAUTHORIZED.getMessage())
                    .code(ResponseCodeEnum.UNAUTHORIZED.getCode())
                    .build();
        }

        //Evaluate if user can proceed
        Optional<BaseResponse> evaluation = userEvaluationService.evaluateUser(user.get());
        if (evaluation.isPresent()) {
            return evaluation.get();
        }

        // User exists but password is incorrect
        if (!passwordEncoder.matches(password, user.get().getPassword())) {
            return BaseResponse.builder()
                    .status(ResponseCodeEnum.UNAUTHORIZED.getStatus())
                    .message(ResponseCodeEnum.UNAUTHORIZED.getMessage())
                    .code(ResponseCodeEnum.UNAUTHORIZED.getCode())
                    .build();
        }

        List<MFAEntity> mfaMethods = mfaRepository.findByUserId(user.get().getId());
        // No MFA case
        if (mfaMethods.isEmpty()) {
            return LoginResponseBean.builder()
                    .accessToken(jwtService.generateAccessToken(user.get()))
                    .refreshToken(jwtService.generateRefreshToken(user.get()))
                    .build();
        }
        // MFA
        // Add into redis use status with pending for provide totp method
        userLoginCacheRepository.save(UserLoginCacheEntity.builder()
                .userId(user.get().getId())
                .action(AdditionalActionEnum.AUTH_METHOD_REQUIRED)
                .build());
        return AdditionalActionRequiredResponseBean.builder()
                .additionalAction(AdditionalActionEnum.AUTH_METHOD_REQUIRED)
                .build();
    }
}
