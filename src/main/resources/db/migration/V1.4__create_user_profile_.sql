CREATE TABLE user_profile
(
    id       SERIAL,
    user_id  INT            NOT NULL,
    sex      VARCHAR(16)    NOT NULL,
    weight   NUMERIC(10, 2) NOT NULL,
    height   NUMERIC(10, 2) NOT NULL,
    age      INT            NOT NULL,
    activity VARCHAR(32)    NOT NULL,
    target   VARCHAR(32)    NOT NULL,
    created  TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (id)
);