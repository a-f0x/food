CREATE TABLE nutrients
(
    id            SERIAL PRIMARY KEY,
    name          VARCHAR(255)   NOT NULL,
    manufacturer  VARCHAR(255)   NOT NULL DEFAULT 'Unknown',
    carbohydrates NUMERIC(10, 2) NOT NULL DEFAULT 0.0,
    proteins      NUMERIC(10, 2) NOT NULL DEFAULT 0.0,
    fats          NUMERIC(10, 2) NOT NULL DEFAULT 0.0,
    kilocalories  NUMERIC(10, 2) NOT NULL DEFAULT 0.0,
    created       TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified      TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP

);
CREATE UNIQUE INDEX on nutrients (name);
CREATE INDEX on nutrients (manufacturer);
