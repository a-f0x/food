CREATE TABLE users
(
    id          SERIAL PRIMARY KEY,
    login       VARCHAR(64)  NOT NULL,
    telegram_id INT          NULL     DEFAULT NULL,
    password    VARCHAR(255) NOT NULL,
    created     TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uq_login UNIQUE (login),
    CONSTRAINT uq_telegram_id UNIQUE (telegram_id)
);
