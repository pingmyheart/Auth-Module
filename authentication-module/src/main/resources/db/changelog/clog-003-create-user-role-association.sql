--liquibase formatted sql
--changeset liquibase:003
CREATE TABLE user_role_association_entity
(
    id      VARCHAR(36) NOT NULL,
    user_id VARCHAR(36) NOT NULL,
    role_id VARCHAR(36) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE user_role_association_entity
    ADD CONSTRAINT unique_user_role UNIQUE (user_id, role_id);
ALTER TABLE user_role_association_entity
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES user_entity (id);
ALTER TABLE user_role_association_entity
    ADD CONSTRAINT fk_role_id FOREIGN KEY (role_id) REFERENCES role_entity (id);
