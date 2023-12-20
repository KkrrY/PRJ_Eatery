DELIMITER //
CREATE TRIGGER delete_related_records
    BEFORE DELETE ON orders
    FOR EACH ROW
BEGIN
    DELETE FROM orders_burgers WHERE orders_id = OLD.id;
    DELETE FROM orders_dishes WHERE orders_id = OLD.id;
    DELETE FROM burger_ingredients where burger_id IN (select id from burger where order_id = OLD.id );
    DELETE FROM burger WHERE order_id = OLD.id;
    DELETE FROM dish WHERE order_id = OLD.id;
END;
//
DELIMITER ;


DELIMITER //

CREATE PROCEDURE ProcessBurgers()
BEGIN
    -- Declare variables
    DECLARE burger_id bigint;
    DECLARE burger_name varchar(255);
    DECLARE burger_order_id bigint;

    -- Declare the cursor
    DECLARE burger_cursor CURSOR FOR
        SELECT id, name, order_id FROM burger;

    -- Declare handler for cursor not found
    DECLARE CONTINUE HANDLER FOR NOT FOUND
        SET burger_id = NULL;

    -- Open the cursor
    OPEN burger_cursor;

    -- Start fetching and processing
    FETCH burger_cursor INTO burger_id, burger_name, burger_order_id;

    WHILE (burger_id IS NOT NULL) DO
            -- Your logic here, for example, printing the values
            SELECT CONCAT('Burger ID: ', burger_id, ', Name: ', burger_name, ', Order ID: ', burger_order_id) AS BurgerInfo;

            -- Fetch the next row
            FETCH burger_cursor INTO burger_id, burger_name, burger_order_id;
        END WHILE;

    -- Close the cursor
    CLOSE burger_cursor;
END //

DELIMITER ;
CALL ProcessBurgers();