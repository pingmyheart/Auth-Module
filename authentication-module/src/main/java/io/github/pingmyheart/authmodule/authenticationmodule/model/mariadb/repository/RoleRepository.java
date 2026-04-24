package io.github.pingmyheart.authmodule.authenticationmodule.model.mariadb.repository;

import io.github.pingmyheart.authmodule.authenticationmodule.model.mariadb.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, String> {
    Optional<RoleEntity> findByRole(String role);

    @Query("SELECT r FROM RoleEntity r " +
            "JOIN UserRoleAssociationEntity  ure ON ure.roleId=r.id " +
            "JOIN UserEntity ue ON ure.userId=ue.id " +
            "WHERE ue.id=:userId")
    List<RoleEntity> findByUserId(String userId);
}
