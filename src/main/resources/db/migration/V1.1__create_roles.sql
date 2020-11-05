CREATE TABLE roles
(
    id   SERIAL PRIMARY KEY,
    role VARCHAR(64),
    CONSTRAINT uq_role_name UNIQUE (role)
);


INSERT INTO roles (role)
VALUES ('ADMIN');

INSERT INTO roles (role)
VALUES ('USER');

INSERT INTO roles (role)
VALUES ('BLOCKED');

INSERT INTO roles (role)
VALUES ('DELETED');

