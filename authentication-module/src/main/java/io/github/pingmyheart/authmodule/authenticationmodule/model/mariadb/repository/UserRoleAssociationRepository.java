package io.github.pingmyheart.authmodule.authenticationmodule.model.mariadb.repository;

import io.github.pingmyheart.authmodule.authenticationmodule.model.mariadb.entity.UserRoleAssociationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleAssociationRepository extends JpaRepository<UserRoleAssociationEntity, String> {
}
