CREATE TABLE food_events
(
    id          SERIAL PRIMARY KEY,
    event_id    INT            NOT NULL,
    food_id     INT            NOT NULL,
    weight_gram NUMERIC(10, 2) NOT NULL,
    FOREIGN KEY (event_id) REFERENCES events (id),
    FOREIGN KEY (food_id) REFERENCES food (id)
);

CREATE INDEX idx_over_event_id ON food_events (event_id);
CREATE INDEX idx_over_food_id ON food_events (food_id);
