DELIMITER //

CREATE PROCEDURE SP_INSERT_MAGASIN (
    IN pName VARCHAR(30),
    IN pStreet VARCHAR(30),
    IN pCity VARCHAR(30),
    IN pPostalCode VARCHAR(30),
    IN pNumber INT,
    IN pArea INT,
    OUT insertedId INT
)
BEGIN
    INSERT INTO stores (name, street, city, postalCode, number, area)
    VALUES (pName, pStreet, pCity, pPostalCode, pNumber, pArea);
    SET insertedId = last_insert_id();
END //

DELIMITER ;