CREATE TABLE store_product_pivot (
	store_id INT,
    product_id INT,
    FOREIGN KEY (store_id) REFERENCES stores (id),
    FOREIGN KEY (product_id) REFERENCES products (id),
    PRIMARY KEY (store_id, product_id)
);