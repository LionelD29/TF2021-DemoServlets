CREATE DATABASE produit_sample;

use produit_sample;

CREATE TABLE products (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(30),
    brand VARCHAR(30),
    price DOUBLE
);