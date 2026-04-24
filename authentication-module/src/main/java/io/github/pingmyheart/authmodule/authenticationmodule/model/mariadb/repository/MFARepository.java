package io.github.pingmyheart.authmodule.authenticationmodule.model.mariadb.repository;

import io.github.pingmyheart.authmodule.authenticationmodule.model.mariadb.entity.MFAEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MFARepository extends JpaRepository<MFAEntity, String> {
    List<MFAEntity> findByUserId(String userId);
}
