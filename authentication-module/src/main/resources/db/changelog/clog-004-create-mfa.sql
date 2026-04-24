--liquibase formatted sql
--changeset liquibase:004
CREATE TABLE mfa_entity
(
    id       VARCHAR(36)  NOT NULL,
    user_id  VARCHAR(36)  NOT NULL,
    mfa_type VARCHAR(36)  NOT NULL,
    data     VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE mfa_entity
    ADD CONSTRAINT unique_id UNIQUE (id);
ALTER TABLE mfa_entity
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES user_entity (id);
