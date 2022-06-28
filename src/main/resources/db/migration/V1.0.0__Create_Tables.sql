CREATE TABLE product
(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR(50)   NOT NULL,
    price NUMERIC(8, 2) NOT NULL
);

CREATE TABLE photo
(
    id          SERIAL PRIMARY KEY,
    bucket      VARCHAR(50) NOT NULL,
    object_name VARCHAR(50) NOT NULL,
    product_id  INTEGER     NOT NULL,
    FOREIGN KEY (product_id) REFERENCES product (id)
);