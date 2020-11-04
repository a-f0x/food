CREATE TABLE nutrients
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    name          VARCHAR(255)   NOT NULL,
    manufacturer  VARCHAR(255)   NOT NULL,
    carbohydrates NUMERIC(10, 2) NOT NULL DEFAULT 0.0,
    proteins      NUMERIC(10, 2) NOT NULL DEFAULT 0.0,
    fats          NUMERIC(10, 2) NOT NULL DEFAULT 0.0,
    kilocalories  NUMERIC(10, 2) NOT NULL DEFAULT 0.0
);
CREATE UNIQUE INDEX on nutrients (name);
CREATE INDEX on nutrients (manufacturer);
