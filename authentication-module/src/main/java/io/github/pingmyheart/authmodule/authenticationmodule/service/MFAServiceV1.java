package io.github.pingmyheart.authmodule.authenticationmodule.service;

import dev.samstevens.totp.code.CodeGenerator;
import dev.samstevens.totp.code.CodeVerifier;
import dev.samstevens.totp.code.DefaultCodeGenerator;
import dev.samstevens.totp.code.DefaultCodeVerifier;
import dev.samstevens.totp.time.SystemTimeProvider;
import dev.samstevens.totp.time.TimeProvider;
import io.github.pingmyheart.authmodule.authenticationmodule.model.mariadb.entity.UserEntity;
import io.github.pingmyheart.authmodule.authenticationmodule.model.mariadb.repository.MFARepository;
import io.github.pingmyheart.authmodule.authenticationmodule.model.mariadb.repository.UserRepository;
import io.github.pingmyheart.authmodule.authenticationmodule.model.redis.entity.UserLoginCacheEntity;
import io.github.pingmyheart.authmodule.authenticationmodule.model.redis.repository.UserLoginCacheRepository;
import io.github.pingmyheart.authmodule.authenticationmoduledto.enums.AdditionalActionEnum;
import io.github.pingmyheart.authmodule.authenticationmoduledto.enums.MFATypeEnum;
import io.github.pingmyheart.authmodule.authenticationmoduledto.enums.ResponseCodeEnum;
import io.github.pingmyheart.authmodule.authenticationmoduledto.request.InitiateMFARequestBean;
import io.github.pingmyheart.authmodule.authenticationmoduledto.request.VerifyLoginRequestBean;
import io.github.pingmyheart.authmodule.authenticationmoduledto.response.AdditionalActionRequiredResponseBean;
import io.github.pingmyheart.authmodule.authenticationmoduledto.response.BaseResponse;
import io.github.pingmyheart.authmodule.authenticationmoduledto.response.LoginResponseBean;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.util.encoders.Base32;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MFAServiceV1 {
    private final SecureRandom secureRandom;
    private final UserLoginCacheRepository userLoginCacheRepository;
    private final MFARepository mfaRepository;
    private final UserRepository userRepository;
    private final JwtServiceV1 jwtService;

    public String generateRandomSecret() {
        byte[] randomBytes = new byte[100];
        secureRandom.nextBytes(randomBytes);
        return new String(Base32.encode(randomBytes));
    }

    private boolean verifyTotp(String secret, String code) {
        TimeProvider timeProvider = new SystemTimeProvider();
        CodeGenerator codeGenerator = new DefaultCodeGenerator();
        CodeVerifier verifier = new DefaultCodeVerifier(codeGenerator, timeProvider);

        return verifier.isValidCode(secret, code);
    }

    public BaseResponse initiate(String userId, InitiateMFARequestBean requestBean) {
        Optional<UserLoginCacheEntity> cache = userLoginCacheRepository.findByUserId(userId);
        if (cache.isEmpty()) {
            return BaseResponse.builder()
                    .status(ResponseCodeEnum.LOGIN_SESSION_NOT_FOUND.getStatus())
                    .message(ResponseCodeEnum.LOGIN_SESSION_NOT_FOUND.getMessage())
                    .code(ResponseCodeEnum.LOGIN_SESSION_NOT_FOUND.getCode())
                    .build();
        }
        //TODO add a processor that based on given mfa type it processes if send mail or else
        cache.get().setChannel(requestBean.getMfaType());
        cache.get().setAction(AdditionalActionEnum.TOTP_REQUIRED);
        userLoginCacheRepository.save(cache.get());
        return AdditionalActionRequiredResponseBean.builder()
                .additionalAction(AdditionalActionEnum.TOTP_REQUIRED)
                .build();
    }

    @SneakyThrows
    public BaseResponse verify(String userId, VerifyLoginRequestBean requestBean) {
        Optional<UserLoginCacheEntity> cache = userLoginCacheRepository.findByUserId(userId);
        if (cache.isEmpty()) {
            return BaseResponse.builder()
                    .status(ResponseCodeEnum.LOGIN_SESSION_NOT_FOUND.getStatus())
                    .message(ResponseCodeEnum.LOGIN_SESSION_NOT_FOUND.getMessage())
                    .code(ResponseCodeEnum.LOGIN_SESSION_NOT_FOUND.getCode())
                    .build();
        }
        switch (cache.get().getChannel()) {
            case APP -> {
                String otpAuthUrl = mfaRepository.findByUserId(userId)
                        .stream()
                        .filter(mfa -> mfa.getMfaType().equals(MFATypeEnum.APP))
                        .findFirst()
                        .get()
                        .getData();
                String secret = splitQuery(new URI(otpAuthUrl)).get("secret");
                if (!verifyTotp(secret, requestBean.getTotp())) {
                    return BaseResponse.builder()
                            .status(ResponseCodeEnum.UNAUTHORIZED.getStatus())
                            .message(ResponseCodeEnum.UNAUTHORIZED.getMessage())
                            .code(ResponseCodeEnum.UNAUTHORIZED.getCode())
                            .build();
                }
                UserEntity userEntity = userRepository.findById(userId).get();
                return LoginResponseBean.builder()
                        .accessToken(jwtService.generateAccessToken(userEntity))
                        .refreshToken(jwtService.generateRefreshToken(userEntity))
                        .build();
            }
            default -> throw new UnsupportedOperationException("MFA type not supported yet");
        }
    }

    private Map<String, String> splitQuery(URI uri) {
        Map<String, String> queryPairs = new HashMap<>();
        String query = uri.getQuery();

        if (query == null) return queryPairs;

        String[] pairs = query.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            String key = URLDecoder.decode(pair.substring(0, idx), StandardCharsets.UTF_8);
            String value = URLDecoder.decode(pair.substring(idx + 1), StandardCharsets.UTF_8);
            queryPairs.put(key, value);
        }
        return queryPairs;
    }
}
