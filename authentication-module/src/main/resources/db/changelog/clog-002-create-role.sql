--liquibase formatted sql
--changeset liquibase:002
CREATE TABLE role_entity
(
    id   VARCHAR(36) NOT NULL,
    role VARCHAR(36) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE role_entity
    ADD CONSTRAINT unique_role UNIQUE (role);
INSERT INTO role_entity (id, role)
VALUES (UUID(), 'admin');
INSERT INTO role_entity (id, role)
VALUES (UUID(), 'user');