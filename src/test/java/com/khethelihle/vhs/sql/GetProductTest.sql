SELECT * FROM PRODUCTS

SELECT * FROM CUSTOMER_ORDERS

SELECT * FROM ORDER_ITEMS

-- Get all data after placing an order.
SELECT
    p.name AS Product_name, p.price AS product_price,
    o.id AS Order_ID, o.quantity AS number_of_parts,
    co.total_amount
FROM
    PRODUCTS p
CROSS JOIN
    ORDER_ITEMS o
CROSS JOIN
    CUSTOMER_ORDERS co

-- Get Product and total amount of orders.

SELECT
    p.name,
    co.total_amount
FROM
    PRODUCTS p
INNER JOIN
    ORDER_ITEMS o ON p.id = o.product_id
INNER JOIN
    CUSTOMER_ORDERS co ON o.order_id = co.id
GROUP BY
    p.id, p.name;