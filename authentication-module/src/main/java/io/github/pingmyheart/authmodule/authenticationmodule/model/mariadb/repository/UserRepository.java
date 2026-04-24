package io.github.pingmyheart.authmodule.authenticationmodule.model.mariadb.repository;

import io.github.pingmyheart.authmodule.authenticationmodule.model.mariadb.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByEmailOrUsername(String email, String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
