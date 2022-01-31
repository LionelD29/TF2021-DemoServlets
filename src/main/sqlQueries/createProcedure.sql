DELIMITER //

CREATE PROCEDURE SP_INSERT_PRODUCT (
    IN pName VARCHAR(30),
    IN pBrand VARCHAR(30),
    IN pPrice DOUBLE,
    OUT insertedId INT
)
BEGIN
	INSERT INTO products (name, brand, price)
    VALUES (pName, pBrand, pPrice);
    SET insertedId = last_insert_id();
END //

DELIMITER ;