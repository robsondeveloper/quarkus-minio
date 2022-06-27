CREATE TABLE photo
(
    id          SERIAL PRIMARY KEY,
    bucket      VARCHAR(50) NOT NULL,
    object_name VARCHAR(50) NOT NULL
);