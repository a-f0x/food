CREATE TABLE nutrients
(
    id                  SERIAL PRIMARY KEY,
    created_by_user_id  INT            NOT NULL,
    modified_by_user_id INT            NOT NULL,
    name                VARCHAR(255)   NOT NULL,
    manufacturer        VARCHAR(255)   NOT NULL DEFAULT 'Unknown',
    carbohydrates       NUMERIC(10, 2) NOT NULL DEFAULT 0.0,
    proteins            NUMERIC(10, 2) NOT NULL DEFAULT 0.0,
    fats                NUMERIC(10, 2) NOT NULL DEFAULT 0.0,
    kilocalories        NUMERIC(10, 2) NOT NULL DEFAULT 0.0,
    created             TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified            TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (created_by_user_id) REFERENCES users (id),
    FOREIGN KEY (modified_by_user_id) REFERENCES users (id)
);
CREATE UNIQUE INDEX on nutrients (name);
CREATE INDEX on nutrients (manufacturer);
