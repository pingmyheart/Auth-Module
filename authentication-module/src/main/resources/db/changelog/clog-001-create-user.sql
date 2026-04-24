--liquibase formatted sql
--changeset liquibase:001
CREATE TABLE user_entity
(
    id               VARCHAR(36)  NOT NULL,
    username         VARCHAR(36)  NOT NULL,
    email            VARCHAR(255) NOT NULL,
    password         VARCHAR(255) NOT NULL,
    creation_date    datetime     NOT NULL,
    last_update_date datetime     NOT NULL,
    enabled          BIT(1)       NOT NULL DEFAULT 1,
    PRIMARY KEY (id)
);

ALTER TABLE user_entity
    ADD CONSTRAINT unique_username UNIQUE (username);
ALTER TABLE user_entity
    ADD CONSTRAINT unique_email UNIQUE (email);