CREATE TABLE food
(
    id                  SERIAL PRIMARY KEY,
    created_by_user_id  INT            NOT NULL,
    modified_by_user_id INT            NOT NULL,
    name                VARCHAR(255)   NOT NULL,
    manufacturer        VARCHAR(255)   NOT NULL DEFAULT 'Unknown',
    carb                NUMERIC(10, 2) NOT NULL DEFAULT 0.0,
    protein             NUMERIC(10, 2) NOT NULL DEFAULT 0.0,
    fat                 NUMERIC(10, 2) NOT NULL DEFAULT 0.0,
    k_cal               NUMERIC(10, 2) NOT NULL DEFAULT 0.0,
    created             TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified            TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (created_by_user_id) REFERENCES users (id),
    FOREIGN KEY (modified_by_user_id) REFERENCES users (id)
);
CREATE UNIQUE INDEX on food (name);
CREATE INDEX on food (manufacturer);
