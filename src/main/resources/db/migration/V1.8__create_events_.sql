CREATE TABLE events
(
    id            SERIAL PRIMARY KEY,
    user_id       INT            NOT NULL,
    event_type    VARCHAR(64)    NOT NULL,
    name          VARCHAR(255)   NOT NULL,
    k_cal         NUMERIC(10, 2) NOT NULL DEFAULT 0.0,
    food_event_id INT            NULL,
    is_removed    BOOLEAN        NOT NULL DEFAULT FALSE,
    created       TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    user_time     TIMESTAMP      NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (food_event_id) REFERENCES food_events (id)

);
CREATE INDEX idx_over_food_event_id ON events (food_event_id);
CREATE INDEX idx_over_event_type ON events (event_type);
CREATE INDEX idx_over_user_id ON events (user_id);
CREATE INDEX idx_over_name ON events (name);
CREATE INDEX idx_over_user_time ON events (user_time);