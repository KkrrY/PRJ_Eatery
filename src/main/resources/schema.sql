CREATE VIEW order_view AS
SELECT
    o.id AS order_id,
    o.order_name,
    o.placed_at,
    o.total_price,
    b.created_at,
    b.name AS burger_name,
    b.id AS burger_id,
    d.dish_order_id AS dish_id,
    rd.name as dish_name

FROM orders o
         LEFT JOIN burgers b ON b.order_id = o.id
         LEFT JOIN orders_dish_order d on d.orders_id = o.id
         LEFT JOIN dishes rd on rd.id = d.dish_order_id ;


# use eatery;
# DELIMITER //
# CREATE TRIGGER delete_related_records
#     BEFORE DELETE ON orders
#     FOR EACH ROW
# BEGIN
#     DELETE FROM orders_burgers WHERE orders_id = OLD.id;
#     DELETE FROM orders_dish_order WHERE orders_id = OLD.id;
#     DELETE FROM burgers_ingredients where burger_id IN (select id from burgers where order_id = OLD.id );
#     DELETE FROM burgers WHERE order_id = OLD.id;
#     DELETE FROM dishes WHERE id = ( select dish_order_id from orders_dish_order where orders_id = OLD.id) ;
# END;
# //
# DELIMITER ;
#
#
# DELIMITER //
# CREATE PROCEDURE ProcessBurgers()
# BEGIN
#     -- Declare variables
#     DECLARE burger_id bigint;
#     DECLARE burger_name varchar(255);
#     DECLARE burger_order_id bigint;
#
#     -- Declare the cursor
#     DECLARE burger_cursor CURSOR FOR
#         SELECT id, name, order_id FROM burgers;
#
#     -- Declare handler for cursor not found
#     DECLARE CONTINUE HANDLER FOR NOT FOUND
#         SET burger_id = NULL;
#
#     -- Open the cursor
#     OPEN burger_cursor;
#
#     -- Start fetching and processing
#     FETCH burger_cursor INTO burger_id, burger_name, burger_order_id;
#
#     WHILE (burger_id IS NOT NULL) DO
#             -- Your logic here, for example, printing the values
#             SELECT CONCAT('Burger ID: ', burger_id, ', Name: ', burger_name, ', Order ID: ', burger_order_id) AS BurgerInfo;
#
#             -- Fetch the next row
#             FETCH burger_cursor INTO burger_id, burger_name, burger_order_id;
#         END WHILE;
#
#     -- Close the cursor
#     CLOSE burger_cursor;
# END //
#
# DELIMITER ;
# CALL ProcessBurgers();
#
# drop procedure ProcessBurgers;
