package io.github.pingmyheart.authmodule.authenticationmodule.model.redis.repository;

import io.github.pingmyheart.authmodule.authenticationmodule.model.redis.entity.UserLoginCacheEntity;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserLoginCacheRepository {
    @CachePut(value = "userLoginCache", key = "#entity.userId")
    public Optional<UserLoginCacheEntity> save(UserLoginCacheEntity entity) {
        return Optional.of(entity);
    }

    @Cacheable(value = "userLoginCache", key = "#userId")
    public Optional<UserLoginCacheEntity> findByUserId(String userId) {
        return Optional.empty();
    }

    @CacheEvict(value = "userLoginCache", key = "#userId")
    public Optional<UserLoginCacheEntity> deleteByUserId(String userId) {
        return Optional.empty();
    }
}
